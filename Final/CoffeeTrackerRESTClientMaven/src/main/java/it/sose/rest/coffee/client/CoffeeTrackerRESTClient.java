package it.sose.rest.coffee.client;

import org.apache.cxf.jaxrs.client.WebClient;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


public class CoffeeTrackerRESTClient {
	private static final String endpoint = "http://localhost:8080/CoffeeTrackerRESTServiceMaven/rest/";

	public static void main(String[] args) {
		WebClient client = WebClient.create(endpoint + "coffeeTracker/addCoffeeTracker/5");
		Response response = client.accept(MediaType.TEXT_PLAIN).get();
		
		String value = response.readEntity(String.class);
		System.out.println(value);
		
	}

}
