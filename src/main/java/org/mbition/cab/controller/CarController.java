package org.mbition.cab.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.mbition.cab.model.Car;
import org.mbition.cab.model.User;
import org.mbition.cab.service.CarService;

@Path("/car")
public class CarController {
	
	CarService carService = new CarService();

	@POST
	@Path("/locate-cars")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Car> findCars(User user) {
		return carService.findCars(user);
	}
}
