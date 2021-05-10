package org.mbition.cab.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.mbition.cab.model.User;
import org.mbition.cab.service.UserService;

@Path("/user")
public class UserController {
	
	UserService userService = new UserService();
	
	@GET
	@Path("/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@PathParam("email") String email) {
		return userService.findUser(email);
	}
	
	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User register(User user) {
		return userService.register(user);
	}
}
