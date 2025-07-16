package it.sose.rest.weather.controller;

import it.sose.rest.weather.service.WeatherTracker;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * REST Controller that exposes endpoints to interact with the WeatherTracker service.
 * 
 * This class uses constructor-based dependency injection to obtain a reference
 * to the WeatherTracker service implementation.
 */
@Tag(name = "Weather Tracker", description = "Provides weather tracking data logic")
@RestController
@RequestMapping("/WeatherTrackerRESTSpring")
public class WeatherControllerwithConstructorInjection {

    /**
     * WeatherTracker service used to handle weather tracker data logic.
     * Declared as final because it's injected via constructor.
     */
	private final WeatherTracker weatherTracker;

    /**
     * Constructor-based dependency injection.
     * Spring will automatically inject the implementation of WeatherTracker.
     * @param weatherTracker the service responsible for weather tracking operations.
     */
    public WeatherControllerwithConstructorInjection(WeatherTracker weatherTracker) {
        this.weatherTracker = weatherTracker;
    }
    
    /**
     * Endpoint to retrieve a formatted print of the entire weather tracking matrix.
     * Example: GET /WeatherTrackerRESTSpring/print
     * @return string representation of weather data by month and day
     */
    @Operation(summary = "Print weather tracking matrix", description = "Returns a formatted string of all recorded weather data by month and day")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Matrix printed successfully"),
    })
    @GetMapping("/print")
    public String print() {
        return weatherTracker.printWeatherTracker();
    }
    
    /**
     * Endpoint to add a new weather value for the current day.
     * The value is passed as a path variable in the URL.
     * Example: GET/WeatherTrackerRESTSpring/add/5
     * @param a integer value (1–7) representing the weather state
     * @return confirmation message or error if the value is invalid
     */
    @Operation(summary = "Add today's weather value", description = "Adds a new weather value for the current day")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Value added successfully"),
            @ApiResponse(responseCode = "200", description = "Invalid weather value")
    })
    @GetMapping("/add/{a}")
    public String add(@PathVariable int a) {
        return weatherTracker.addWeatherTracker(a);
    }
    
    /**
     * Endpoint to retrieve the values recorded for the last 7 days (excluding today).
     * Example: GET/WeatherTtackerRESTSpring/lastValues
     * @return array-style string of the last 7 days' values
     */
    @Operation(summary = "Get last 7 days' values", description = "Retrieves weather values recorded in the last 7 days (excluding today)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved weather values")
    })
    @GetMapping("/lastValues")
    public String lastValues() {
        return weatherTracker.last7DaysValues();
    }
}