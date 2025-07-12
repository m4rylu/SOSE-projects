package it.sose.advice.coffee;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * Interface defining RESTful endpoints for the AdviceCoffee service.
 * Uses JAX-RS annotations to specify HTTP methods, paths, and response types.
 */
public interface AdviceCoffee {
	
	/**
	 * Exposes a REST endpoint that handles HTTP GET requests at the path "/advice".
	 * Returns personalized advice for the user based on data from two services:
	 * the CoffeeTracker service and the SleepTracker service.
	 * @return a text advice message tailored to the user's data
	 */
	@Operation(summary = "Print coffee and sleep advice ",
            description = "Returns a formatted string with a personalized advice based on the last values of the user")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Advice printed successfully")
	})
	@GET
	@Path("/advice")
	@Produces(MediaType.TEXT_PLAIN)
	String getAdviceCoffee();
	
	
	
}
