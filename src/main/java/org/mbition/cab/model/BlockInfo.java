package org.mbition.cab.model;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class BlockInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private User user;
	private Car car;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}
}
