package it.sose.rest.coffee;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * Interface defining RESTful endpoints for the CoffeeTracker service.
 * Uses JAX-RS annotations to specify HTTP methods, paths, and response types.
 */
public interface CoffeeTracker {
	
	/**
	 * Exposes a REST operation that handles HTTP GET requests at the path "/print"
     * Returns a formatted string representing all stored coffee numbers
     * for each day of the year, organized by month and day.
     * @return a human-readable table of recorded rate values
     */
	@Operation(summary = "Print coffee tracking matrix",
            description = "Returns a formatted string of all recorded coffee data by month and day")
		@ApiResponses(value = {
				@ApiResponse(responseCode = "200", description = "Matrix printed successfully")
	})
	@GET
	@Path("/print")
	@Produces(MediaType.TEXT_PLAIN)
	String printCoffeeTracker ();
	
	/**
	 * Exposes a REST operation that handles HTTP GET requests at the path "/add/{a}", where {a} is a path parameter.
     * Records a new coffee number for the current day.
     * @param a the coffee taken number to record for today
     * @return a confirmation message or an error message if input is invalid
     */
    @Operation(summary = "Add today's coffee number", description = "Adds a new coffee taken number for the current day")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Value added successfully"),
    })
	@GET
	@Path("/add/{a}")
	@Produces((MediaType.TEXT_PLAIN))
	String addCoffeeTracker(@PathParam("a")int a);
	
	/**
	 * Exposes a REST operation named "lastValues" handles HTTP GET requests at the path "/lastValues".
     * Retrieves the recorded weather values for the past 7 days (excluding today).
     * @return a string representation (e.g. array format) of the last 7 days' values
     */
    @Operation(summary = "Get last 7 days' values", description = "Retrieves coffee numbers recorded in the last 7 days (excluding today)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved coffee number")
    })
	@GET
	@Path("/lastValues")
	@Produces((MediaType.TEXT_PLAIN))
	String last7DaysValues();
	
}