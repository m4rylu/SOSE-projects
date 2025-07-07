package it.sose.advice.coffee;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

public interface AdviceCoffee {
	@GET
	@Path("/advice")
	@Produces(MediaType.TEXT_PLAIN)
	String getAdviceCoffee();
	
	
	
}
