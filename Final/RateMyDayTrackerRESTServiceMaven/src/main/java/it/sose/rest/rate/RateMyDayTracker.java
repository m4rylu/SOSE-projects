package it.sose.rest.rate;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

public interface RateMyDayTracker {
	@GET
	@Path("/print")
	@Produces(MediaType.TEXT_PLAIN)
	String printRateMyDayTracker ();
	
	@GET
	@Path("/add/{a}")
	@Produces((MediaType.TEXT_PLAIN))
	String addRateMyDayTracker(@PathParam("a")int a);
	
	@GET
	@Path("/lastValues")
	@Produces((MediaType.TEXT_PLAIN))
	String last7DaysValues();
	
}
