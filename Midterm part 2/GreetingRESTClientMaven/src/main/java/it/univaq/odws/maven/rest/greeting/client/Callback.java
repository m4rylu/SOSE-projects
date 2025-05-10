package it.univaq.odws.maven.rest.greeting.client;

import jakarta.ws.rs.client.InvocationCallback;
import jakarta.ws.rs.core.Response;

public class Callback implements InvocationCallback<Response> {
	private String messageFromTheServer;

	@Override
	public void completed(Response response) {
		if (response.getStatus() == 200) {
			messageFromTheServer = response.readEntity(String.class);
		} else {
			System.err.println("Request error: " + response.getStatus());
		}
	}

	@Override
	public void failed(Throwable throwable) {
		throwable.printStackTrace();
	}

	public String getResponse() {
		return messageFromTheServer;
	}
}
