package org.mbition.cab.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "car")
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "model")
	private String model;
	
	@Column(name = "rent_per_min")
	private long rentPerMin;
	
	@Column(name = "available")
	private boolean available;
	
	@OneToOne
	@JoinColumn(name = "location_id")
	private Location location;

	public Car() {	}
	
	public Car(String name, String model, int rentPerMin, boolean available, Location location) {
		this.name = name;
		this.model = model;
		this.rentPerMin = rentPerMin;
		this.available = available;
		this.location = location;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public long getRentPerMin() {
		return rentPerMin;
	}

	public void setRentPerMin(long rentPerMin) {
		this.rentPerMin = rentPerMin;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
}
