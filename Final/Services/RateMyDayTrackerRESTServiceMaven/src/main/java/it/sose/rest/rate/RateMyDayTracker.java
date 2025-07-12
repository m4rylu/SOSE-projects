package it.sose.rest.rate;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * Interface defining RESTful endpoints for the RateMyDayTracker service.
 * Uses JAX-RS annotations to specify HTTP methods, paths, and response types.
 */
public interface RateMyDayTracker {
	
	/**
	 * Exposes a REST operation that handles HTTP GET requests at the path "/print"
     * Returns a formatted string representing all stored rate values
     * for each day of the year, organized by month and day.
     * @return a human-readable table of recorded rate values
     */
	@Operation(summary = "Print rate tracking matrix",
            description = "Returns a formatted string of all recorded rate data by month and day")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Matrix printed successfully")
	})
	@GET
	@Path("/print")
	@Produces(MediaType.TEXT_PLAIN)
	String printRateMyDayTracker ();
	
	
	/**
	 * Exposes a REST operation that handles HTTP GET requests at the path "/add/{a}", where {a} is a path parameter.
     * Records a new rate value for the current day.
     * @param a the rate value to record for today
     * @return a confirmation message or an error message if input is invalid
     */
    @Operation(summary = "Add today's rate value", description = "Adds a new rate value for the current day")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Value added successfully"),
    })
	@GET
	@Path("/add/{a}")
	@Produces((MediaType.TEXT_PLAIN))
	String addRateMyDayTracker(@PathParam("a")int a);
	
	/**
	 * Exposes a REST operation named "lastValues" handles HTTP GET requests at the path "/lastValues".
     * Retrieves the recorded weather values for the past 7 days (excluding today).
     * @return a string representation (e.g. array format) of the last 7 days' values
     */
    @Operation(summary = "Get last 7 days' values", description = "Retrieves weather values recorded in the last 7 days (excluding today)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved weather values")
    })
	@GET
	@Path("/lastValues")
	@Produces((MediaType.TEXT_PLAIN))
	String last7DaysValues();
	
}
