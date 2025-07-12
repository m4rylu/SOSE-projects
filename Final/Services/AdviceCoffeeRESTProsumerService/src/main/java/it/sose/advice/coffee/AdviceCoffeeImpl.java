package it.sose.advice.coffee;

import java.util.concurrent.Future;
import it.sose.soap.sleep.SleepTrackerPort;
import it.sose.soap.sleep.SleepTrackerPortService;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Response;
import jakarta.xml.ws.BindingProvider;
import it.sose.soap.sleep.LastValuesRequest;


/*
 * Implementation of the AdviceCoffee service that provides personalized advice
 * by aggregating data from two external services:
 * <ul>
 *   <li><b>CoffeeTracker (REST Service)</b>: Last7DaysValues - Returns the last 7 days number of coffee taken</li>
 *   <li><b>SleepTracker (SOAP Service)</b>: Last7DaysValues - Returns the last 7 days hours of sleep</li>
 * </ul>
 * 
 */
@Path("/")
public class AdviceCoffeeImpl implements AdviceCoffee{
	
	@Override
	public String getAdviceCoffee() {
		
		String[] values;
		int coffeeSum = 0;
		int sleepSum = 0;
		String advice;
		
		// Create a REST client using JAX-RS Client API for the coffee service
		Client client = ClientBuilder.newClient();
		// Create an instance of the Callback class to handle asynchronous REST response
		Callback coffeeHandler = new Callback();
		// Asynchronous GET call to the REST endpoint for last 7 values passing the handler to the get function
		Future<Response> futureCoffeeResponse = client.target("http://coffee-service:8080/CoffeeTrackerRESTServiceMaven/coffee/lastValues").request().async().get(coffeeHandler);
		
		// Create a SOAP service client from generated artifacts
		SleepTrackerPortService service = new SleepTrackerPortService();
		// Retrieve the SOAP port from the generated service class
		SleepTrackerPort endpoint = service.getSleepTrackerPortSoap11();
		
		// Override endpoint address (because we're using using Docker)
        ((BindingProvider) endpoint).getRequestContext().put(
                BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                "http://sleep-service:8082/services/sleepTracker"
            );
		
        // Prepare and send asynchronous SOAP request for LastValues operation
		LastValuesRequest request = new LastValuesRequest();
		SOAPAsynchHandler sleepHandler = new SOAPAsynchHandler();
		// Send asynchronous SOAP request to invoke the lastValues operation, 
		// providing a callback handler to process the response when it arrives
		Future<?> futureSleepResponse = endpoint.lastValuesAsync(request, sleepHandler);

		// Wait for both async responses to complete 
		while(!futureCoffeeResponse.isDone() || !futureSleepResponse.isDone()) {
			//The prosumer wait for both the result syncronizing the 2 processes
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		
		// Extract responses
		String coffeeResponse = coffeeHandler.getResponse();
		String sleepResponse = sleepHandler.getResponse().getReturn();
		
		// Check if the responses are valid
	    if (coffeeResponse == null || sleepResponse == null) {
	        return "Errore nel recuperare i dati dai servizi.";
	    }

        // Parsing of the REST response
        coffeeResponse = coffeeResponse.replaceAll("[\\[\\]\\s]", "");        // rimuove [, ], e spazi
        values = coffeeResponse.split(",");

        for (int i = 0; i < values.length && i < 7; i++) {
            coffeeSum += Integer.parseInt(values[i]);
        }
	    
        // Parsing of SOAP response
        sleepResponse = sleepResponse.replaceAll("[\\[\\]\\s]", "");        // rimuove [, ], e spazi
        values = sleepResponse.split(",");

        for (int i = 0; i < values.length && i < 7; i++) {
            sleepSum += Integer.parseInt(values[i]);
        }
	    	
        // Select advice based on aggregated data
		if(coffeeSum<=35 && sleepSum>=56 ) {
			advice="Great job! You drank the recommended amount of coffee and got enough sleep.";
		} else if(coffeeSum>35 && sleepSum>=56) {
			advice="You’ve had a bit too much coffee, but at least you slept well :)";
		} else if(coffeeSum>35 && sleepSum<56) {
			advice="You seem a bit stressed... too much coffee and not enough sleep :/";
		} else {
			advice="You drank a good amount of coffee, but you didn’t get enough sleep.";
		}
		
		return advice;
	}
}



