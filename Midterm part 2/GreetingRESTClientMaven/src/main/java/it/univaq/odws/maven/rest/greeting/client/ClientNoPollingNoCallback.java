package it.univaq.odws.maven.rest.greeting.client;

import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Response;

public class ClientNoPollingNoCallback {
	private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private static final String endpoint = 
			                      "http://localhost:8080/GreetingRESTServiceMaven/rest/";

	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();
		System.out.println(formatter.format(new Date()) + 
                " - invoking the say_hello asynch rest operation ");
		
		Response response = client.target(endpoint + 
				"greetingrestserviceasync/say_hello/pippo").request().get();
		
		System.out.println(formatter.format(new Date()) + 
				   " - control received");
		
	    String value = response.readEntity(String.class);
		
	    System.out.println(formatter.format(new Date()) + 
				   " - service responded through callback with: " + value);	}
}
