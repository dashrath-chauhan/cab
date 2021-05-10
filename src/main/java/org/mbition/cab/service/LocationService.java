package org.mbition.cab.service;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.mbition.cab.MyResource;
import org.mbition.cab.model.Car;
import org.mbition.cab.model.Location;

public class LocationService {
	
	protected Location validateAndReturnLocation(String destination) {
		
		Session session = MyResource.getSessionFactory().openSession();
		Location location = null;
		
		try {
			location = (Location) session
			    	.createQuery("FROM Location l WHERE l.name = :location")
			    	.setParameter("location", destination)
			    	.getSingleResult();
		} catch(HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return location;
	}
}
