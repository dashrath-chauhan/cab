package org.mbition.cab.service;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.mbition.cab.MyResource;
import org.mbition.cab.model.Location;
import org.mbition.cab.model.Transaction;
import org.mbition.cab.model.User;

public class UserService {
	
	LocationService locationService = new LocationService();
	
	public User findUser(String email) {
		Session session = MyResource.getSessionFactory().openSession();
		User user = null;
		
		try {
			user = (User) session
				    	.createQuery("FROM User u WHERE u.email = :email")
				    	.setParameter("email", email)
				    	.getSingleResult();
		} catch(HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return user;
	}
	
	public User register(User user) {
		Session session = MyResource.getSessionFactory().openSession();
		User newUser = null;
		Location location = locationService.validateAndReturnLocation(user.getLocation().getName());
		
		try {
			session.beginTransaction();
			newUser = createAndReturnUser(user,location,session);
		} catch(HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return newUser;
	}
	
	private User createAndReturnUser(User user, Location location, Session session) {
		User newUser = new User();
		newUser.setName(user.getName());
		newUser.setEmail(user.getEmail());
		newUser.setBalance(user.getBalance());
		newUser.setPassword(user.getPassword());
		newUser.setLocation(location);
		
		Integer id = (Integer) session.save(newUser);
		newUser = session.get(User.class, id);
		session.getTransaction().commit();
		
		return newUser;
	}
	
	protected void payRent(Transaction transaction, long rent) {
		Session session = MyResource.getSessionFactory().openSession();
		User user = transaction.getUser();
		Location location = locationService.validateAndReturnLocation(transaction.getDestination().getName());
		
		try {
			session.beginTransaction();
			
			user.setBalance(user.getBalance() - rent);
			user.setLocation(location);
			
			session.update(user);
			session.getTransaction().commit();
		} catch(HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	protected User validateAndReturnUser(User user) {
		return findUser(user.getEmail());
	}
}
