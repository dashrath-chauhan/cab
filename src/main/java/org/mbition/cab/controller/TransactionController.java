package org.mbition.cab.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.mbition.cab.model.BlockInfo;
import org.mbition.cab.model.Transaction;
import org.mbition.cab.model.User;
import org.mbition.cab.service.TransactionService;

@Path("/transact")
public class TransactionController {
	
	TransactionService transactionService = new TransactionService();
	
	@POST
	@Path("/block-car")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Transaction blockCar(BlockInfo blockInfo) {
		return transactionService.blockCar(blockInfo);
	}
	
	@POST
	@Path("/user-transaction")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Transaction getUserTransaction(User user) {
		return transactionService.getUserTransaction(user);
	}
	
	@POST
	@Path("/end-transaction")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Transaction endUserTransaction(User user) {
		return transactionService.endUserTransaction(user);
	}
}
