package it.univaq.odws.maven.rest.greeting.client;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Response;

public class ClientAsyncBlockingPolling {
	private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private static final String endpoint =
			                        "http://localhost:8080/GreetingRESTServiceMaven/rest/";

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		Client client = ClientBuilder.newClient();

		System.out.println(formatter.format(new Date()) + " - invoking the "
				                                      + "say_hello asynch rest operation");

		Future<Response> futureResponse = client.target(endpoint +
				       "greetingrestserviceasync/say_hello/pippo").request().async().get();

		// block until complete
		Response response = futureResponse.get();
		// Response response = futureResponse.get(1L, TimeUnit.SECONDS);

		String value = response.readEntity(String.class);
		System.out.println(formatter.format(new Date()) + 
				                                    " - service responded with: " + value);
	}

}
