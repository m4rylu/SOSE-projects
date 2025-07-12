package it.sose.data.analysis;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * Interface defining RESTful endpoints for the DataAnalysis service.
 * Uses JAX-RS annotations to specify HTTP methods, paths, and response types.
 */
public interface DataAnalysis {

	/**
	 * Exposes a REST endpoint that handles HTTP GET requests at the path "/sleepData".
	 * Returns personalized summary for the user based on his data from the sleep tracker service
	 * @return a text numerical summary based on the user's sleep data.
	 */
	@Operation(
		    summary = "Get sleep data analysis for the past week",
		    description = "Calls the SleepTracker SOAP service asynchronously to retrieve sleep values for the last 7 days, calculates statistics, serializes data, and returns a summary."
		)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Values print successfully"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GET
	@Path("/sleepData")
	@Produces(MediaType.TEXT_PLAIN)
	String getSleepData() throws IOException, InterruptedException, ExecutionException;
	
	/**
	 * Exposes a REST endpoint that handles HTTP GET requests at the path "/coffeeData".
	 * Returns personalized summary for the user based on his data from the coffee tracker service
	 * @return a text numerical summary based on the user's coffee data.
	 */
	@Operation(
		    summary = "Get coffee data analysis for the past week",
		    description = "Calls the CoffeeTracker REST service asynchronously to retrieve coffee values for the last 7 days, calculates statistics, serializes data, and returns a summary."
		)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Values print successfully"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GET
	@Path("/coffeeData")
	@Produces(MediaType.TEXT_PLAIN)
	String getCoffeeData() throws IOException, InterruptedException, ExecutionException;
	
	/**
	 * Exposes a REST endpoint that handles HTTP GET requests at the path "/humorData".
	 * Returns personalized summary for the user based on data from the humor tracker service
	 * @return a text numerical summary based on the user's humor data.
	 */
	@Operation(
		    summary = "Get humor data analysis for the past week",
		    description = "Calls the UmorTracker SOAP service asynchronously to retrieve humor values for the last 7 days, calculates statistics, serializes data, and returns a summary."
		)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Values print successfully"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GET
	@Path("/humorData")
	@Produces(MediaType.TEXT_PLAIN)
	String getHumorData() throws IOException, InterruptedException, ExecutionException;
	
}