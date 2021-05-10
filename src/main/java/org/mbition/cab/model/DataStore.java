package org.mbition.cab.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataStore {
	
	private Map<String, User> users = new HashMap<>();
	private List<Car> cars = new ArrayList<>();
	private Map<String, Transaction> transactions = new HashMap<>();
	private Map<String, Double> lat = new HashMap<>();
	private Map<String, Double> lon = new HashMap<>();
	
	private static DataStore instance = new DataStore();
	public static DataStore getInstance() {
		return instance;
	}
	
	private DataStore() {
//		lat.put("Ahemdabad", 22.698637);	lon.put("Ahemdabad", 73.115573);
//		lat.put("Vadodara", 22.698538);		lon.put("Vadodara", 73.114303);	
//		lat.put("Surat", 22.697014);		lon.put("Surat", 73.107787);
//		lat.put("Anand", 22.698617);		lon.put("Anand", 73.115640);
//		lat.put("Nadiad", 22.701072);		lon.put("Nadiad", 73.111302);
//		lat.put("Umreth", 22.695707);		lon.put("Umreth", 73.119411);
//		lat.put("Rajkot", 22.692652);		lon.put("Rajkot", 73.115334);
//		lat.put("Baruch", 22.706060);		lon.put("Baruch", 73.119992);
//		lat.put("Bhuj", 22.692989);			lon.put("Bhuj", 73.114603);
//		lat.put("Daman", 22.704523);		lon.put("Daman", 73.116061);
//		
//		users.put("user1@email.com", new User(1L, "User One", "user1@gmail.com", "user1", "50000", lat.get("Anand"), lon.get("Anand")));
//		users.put("user2@email.com", new User(2L, "User Two", "user2@gmail.com", "user2", "50000", lat.get("Nadiad"), lon.get("Nadiad")));
		
//		cars.add(new Car("Honda","Civic", 80, Boolean.TRUE, lat.get("Ahemdabad"), lon.get("Ahemdabad")));
//		cars.add(new Car("Honda","Accord", 100, Boolean.TRUE, lat.get("Vadodara"), lon.get("Vadodara")));
//		cars.add(new Car("VW","Arteon", 200, Boolean.TRUE, lat.get("Surat"), lon.get("Surat")));
//		cars.add(new Car("VW","Passat", 220, Boolean.TRUE, lat.get("Anand"), lon.get("Anand")));
//		cars.add(new Car("Mercedez-Benz","CLA", 220, Boolean.TRUE, lat.get("Nadiad"), lon.get("Nadiad")));
//		cars.add(new Car("Mercedez","GLA", 250, Boolean.TRUE, lat.get("Umreth"), lon.get("Umreth")));
//		cars.add(new Car("Audi","A1", 230, Boolean.TRUE, lat.get("Rajkot"), lon.get("Rajkot")));
//		cars.add(new Car("Audi","A3", 260, Boolean.TRUE, lat.get("Baruch"), lon.get("Baruch")));
//		cars.add(new Car("BMW","X1", 220, Boolean.TRUE, lat.get("Bhuj"), lon.get("Bhuj")));
//		cars.add(new Car("BMW","X2", 190, Boolean.TRUE, lat.get("Daman"), lon.get("Daman")));
	}

	public Map<String, User> getUsers() {
		return users;
	}

	public List<Car> getCars() {
		return cars;
	}

	public Map<String, Transaction> getTransactions() {
		return transactions;
	}

	public Map<String, Double> getLat() {
		return lat;
	}

	public Map<String, Double> getLon() {
		return lon;
	}
}
