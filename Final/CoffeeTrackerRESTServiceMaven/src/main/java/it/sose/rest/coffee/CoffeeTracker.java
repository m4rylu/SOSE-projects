package it.sose.rest.coffee;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/coffeeTracker")
public interface CoffeeTracker {
	
	@GET
	@Path("/printCoffeeTracker")
	@Produces(MediaType.TEXT_PLAIN)
	String printCoffeeTracker ();
	
	@GET
	@Path("/addCoffeeTracker/{a}")
	@Produces((MediaType.TEXT_PLAIN))
	String addCoffeeTracker(@PathParam("a")int a);
	
	
}