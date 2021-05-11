package org.mbition.cab.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.mbition.cab.MyResource;
import org.mbition.cab.model.BlockInfo;
import org.mbition.cab.model.Car;
import org.mbition.cab.model.Location;
import org.mbition.cab.model.Transaction;
import org.mbition.cab.model.User;

public class TransactionService {

	CarService carService = new CarService();
	UserService userService = new UserService();
	LocationService locationService = new LocationService();
	
	public Transaction blockCar(BlockInfo blockInfo) {
		Session session = MyResource.getSessionFactory().openSession();
		Transaction transaction = null;
		
		User user = userService.validateAndReturnUser(blockInfo.getUser());
		Car car = carService.validateAndReturnCar(blockInfo.getCar());
		Location location = locationService.validateAndReturnLocation(blockInfo.getUser().getDestination());
		
		try {
			session.beginTransaction();
			
			transaction = createAndReturnTransaction(user,car,location,session);
			carService.blockCar(car);
		} catch(HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return transaction;
	}
	
	private Transaction createAndReturnTransaction(User user, Car car, Location location, Session session) {
		DateFormat formatter = new SimpleDateFormat("dd:MM:yyyy HH:mm:ss");
		Transaction newTransaction = new Transaction();
		
		newTransaction.setUser(user);
		newTransaction.setCar(car);
		newTransaction.setDestination(location);
		newTransaction.setStartTime(formatter.format(new Date()));
		newTransaction.setPaid(Boolean.FALSE);

		Integer id = (Integer) session.save(newTransaction);
		newTransaction = session.get(Transaction.class, id);
		session.getTransaction().commit();
		
		return newTransaction;
	}

	public Transaction getUserTransaction(User user) {
		Session session = MyResource.getSessionFactory().openSession();
		Transaction transaction = null;
		User nuser = userService.validateAndReturnUser(user);
		try {
			transaction = (Transaction) session
				    	.createQuery("FROM Transaction t WHERE t.user.id = :user and t.paid = :paid")
				    	.setParameter("user", nuser.getId())
				    	.setParameter("paid", Boolean.FALSE)
				    	.getSingleResult();
		} catch(HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return transaction;
	}

	public Transaction endUserTransaction(User user) {
		Session session = MyResource.getSessionFactory().openSession();
		Transaction transaction = getUserTransaction(user);
		
		try {
			session.beginTransaction();
			//close transaction
			closeTransaction(transaction);
			//make car available
			carService.releaseCar(transaction);
		} catch(HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return transaction;
	}

	private void closeTransaction(Transaction transaction) {
		Session session = MyResource.getSessionFactory().openSession();
		DateFormat formatter = new SimpleDateFormat("dd:MM:yyyy HH:mm:ss");
		Date startDateTime = null, endDateTime = null;
		Date endTime = new Date();
		try {
			startDateTime = formatter.parse(transaction.getStartTime());
			endDateTime = formatter.parse(formatter.format(endTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}	
		long difference_In_Time = endDateTime.getTime() - startDateTime.getTime();
		long difference_In_Seconds = TimeUnit.MILLISECONDS.toSeconds(difference_In_Time) % 60;
		long difference_In_Minutes = TimeUnit.MILLISECONDS.toMinutes(difference_In_Time) % 60;
		long difference_In_Hours = TimeUnit.MILLISECONDS.toHours(difference_In_Time) % 24;
		
		long rent = difference_In_Minutes * transaction.getCar().getRentPerMin();
	
		try {
			session.beginTransaction();
			
			//pay rent for the trip
			userService.payRent(transaction,rent);
			transaction.setPaid(Boolean.TRUE);
			transaction.setEndTime(formatter.format(endTime));
			
			session.update(transaction);
			session.getTransaction().commit();
		} catch(HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
