package it.sose.advice.mood;

import it.sose.soap.umor.UmorTracker;
import it.sose.soap.umor.UmorTrackerImplService;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import it.sose.soap.umor.LastValues;
import it.sose.soap.umor.LastValuesResponse;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.xml.ws.BindingProvider;

/*
 * Implementation of the AdviceMood service that provides personalized advice
 * by aggregating data from two external services:
 * <ul>
 *   <li><b>RateMyDayTracker (REST Service)</b>: Last7DaysValues - Returns the last 7 daily ratings</li>
 *   <li><b>UmorTracker (SOAP Service)</b>: Last7DaysValues - Returns the last 7 mood values</li>
 * </ul>
 * 
 * Both services are called asynchronously using a callback approach to improve performance.
 * This method allows the service to wait for both responses in parallel rather than sequentially,
 * reducing the overall waiting time.
 *
 * Asyncronicity is used instead of blocking or synchronous calls because the SOAP service can take up to 20 seconds to respond, 
 * that summed to the REST service response time, risks triggering a timeout
 * in a typical request-response cycle. By using callback with asynchronous calls, 
 * we avoid blocking the thread and can safely check for completion, improving resilience 
 * and avoiding service unavailability.
 */
public class AdviceMoodImpl implements AdviceMood{
	
	@Override
	public String adviceMood() throws InterruptedException, ExecutionException {
		String advice;
		String[] values;
		int rateSum = 0;
		int umorSum = 0;

		// Create a REST client using JAX-RS Client API for the rate service
		Client client = ClientBuilder.newClient();
		// Asynchronous GET call to the REST endpoint for last 7 values
		Future<jakarta.ws.rs.core.Response> rateMyDayResponse = client.target("http://rate-service:8084/RateMyDayTrackerRESTServiceMaven/rate/lastValues").request().async().get();
		
		// Create a SOAP service client from generated artifacts
		UmorTrackerImplService service = new UmorTrackerImplService();
		// Retrieve the SOAP port from the generated service class
		UmorTracker endpoint = service.getUmorTrackerImplPort();
		
		// Override endpoint address (because we're using using Docker)
        ((BindingProvider) endpoint).getRequestContext().put(
                BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                "http://humor-service:8085/UmorTrackerSOAPServiceMaven/umor"
            );
		
    	// Prepare and send asynchronous SOAP request for LastValues operation
		LastValues request = new LastValues();
		jakarta.xml.ws.Response<LastValuesResponse> umorResponse = endpoint.lastValuesAsync(request);
		
		// Wait for both async responses to complete 
		while( !(rateMyDayResponse.isDone() && umorResponse.isDone()) ) {
				Thread.sleep(1000);
		}
		
		// Extract and parse REST response
		String rateMyDayData = rateMyDayResponse.get().readEntity(String.class);
	
        rateMyDayData = rateMyDayData.replaceAll("[\\[\\]\\s]", "");        // rimuove [, ], e spazi
        values = rateMyDayData.split(",");
        for (int i = 0; i < values.length && i < 7; i++) {
            rateSum += Integer.parseInt(values[i]);
        }
        

        // Extract and parse SOAP response
		String umorData = umorResponse.get().getReturn();
		
        umorData = umorData.replaceAll("[\\[\\]\\s]", "");        // rimuove [, ], e spazi
        values = umorData.split(",");
        for (int i = 0; i < values.length && i < 7; i++) {
            umorSum = Integer.parseInt(values[i]);
        }

        // Select advice based on aggregated data
		if(rateSum<=15 && umorSum>=20 ) {
			advice="Even though your days have been tough, your mood has remained high — well done!";
		} else if(rateSum>15 && umorSum>=20) {
			advice="You've had great days and your mood is soaring — keep it up!";
		} else if(rateSum>15 && umorSum<20) {
			advice="Your days were good, but something seems to be bothering you.";
		} else {
			advice="It’s been a rough week, and it’s okay to feel down. Better days are coming!";
		}
		
		return advice;
	}
	
}
