package it.univaq.odws.maven.rest.sum.client;

import org.apache.cxf.jaxrs.client.WebClient;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


public class SumRESTClient {
	private static final String endpoint = "http://localhost:8080/SumRESTServiceMaven/rest/";

	public static void main(String[] args) {
		WebClient client = WebClient.create(endpoint + "sumrestservice/sum?a=5&b=5");
		Response response = client.accept(MediaType.TEXT_PLAIN).get();
		
		String value = response.readEntity(String.class);
		System.out.println("The response is " + value);
		

	}

}

