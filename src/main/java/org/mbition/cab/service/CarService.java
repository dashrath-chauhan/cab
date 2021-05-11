package org.mbition.cab.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.mbition.cab.MyResource;
import org.mbition.cab.model.Car;
import org.mbition.cab.model.Location;
import org.mbition.cab.model.Transaction;
import org.mbition.cab.model.User;

public class CarService {
	
	LocationService locationService = new LocationService();
	UserService userService = new UserService();

	public List<Car> findCars(User user) {
		Session session = MyResource.getSessionFactory().openSession();
		
		List<Car> cars = new ArrayList<>();
		try {
			cars = session.createQuery("FROM Car c WHERE c.available = :available")
			    	.setParameter("available", Boolean.TRUE)
			    	.getResultList();
		} catch(HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		//filter cars within 1KM
		cars = filterCars(user, cars);
		
		return cars;
	}

	private List<Car> filterCars(User user, List<Car> cars) {
		List<Car> filteredCars = new ArrayList<>();
		User vUser = userService.validateAndReturnUser(user);
		cars.stream().forEach(car -> {
			double dist = distance(vUser,car);
			
			if(dist <= new Double(1)) {
				filteredCars.add(car);
			}
		});
		
		return filteredCars;
	}

	private double distance(User user, Car car) {
		double lat1 = user.getLocation().getLatitude();
		double lon1 = user.getLocation().getLongitude();
		double lat2 = car.getLocation().getLatitude();
		double lon2 = car.getLocation().getLongitude();
		
		// The math module contains a function named toRadians which converts from degrees to radians.
		lon1 = Math.toRadians(lon1);
		lon2 = Math.toRadians(lon2);
		lat1 = Math.toRadians(lat1);
		lat2 = Math.toRadians(lat2);

		// Haversine formula
		double dlon = lon2 - lon1;
		double dlat = lat2 - lat1;
		double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);

		double c = 2 * Math.asin(Math.sqrt(a));

		// Radius of earth in kilometers. Use 3956 for miles
		double r = 6371;

		// calculate the result
		return (c * r);
	}

	protected void blockCar(Car car) {
		Session session = MyResource.getSessionFactory().openSession();
		
		try {
			session.beginTransaction();
			
			car.setAvailable(Boolean.FALSE);
			
			session.update(car);
			session.getTransaction().commit();
		} catch(HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	protected void releaseCar(Transaction transaction) {
		Session session = MyResource.getSessionFactory().openSession();
		Car car = transaction.getCar();
		Location location = locationService.validateAndReturnLocation(transaction.getDestination().getName());
		
		try {
			session.beginTransaction();
			
			car.setAvailable(Boolean.TRUE);
			car.setLocation(location);
			
			session.update(car);
			session.getTransaction().commit();
		} catch(HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	protected Car validateAndReturnCar(Car car) {
		Session session = MyResource.getSessionFactory().openSession();
		Car vCar = null;
		
		try {
			vCar = (Car) session
				    	.createQuery("FROM Car c WHERE c.id = :id")
				    	.setParameter("id", car.getId())
				    	.getSingleResult();
		} catch(HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return vCar;
	}
}

interface Specification<T> {
	boolean isSatisfied(T item);
}

interface Filter<T> {
	Stream<T> filter(List<T> t, Specification<T> s);
}

class DistanceSpecification implements Specification<User> {

	@Override
	public boolean isSatisfied(User u) {
		return true;
	}
}

class DistanceFilter implements Filter<Car> {

	@Override
	public Stream<Car> filter(List<Car> cars, Specification<Car> spec) {
		return cars.stream().filter(car -> spec.isSatisfied(car));
	}
	
}
