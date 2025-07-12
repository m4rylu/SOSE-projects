package it.sose.advice.coffee;

import jakarta.ws.rs.client.InvocationCallback;
import jakarta.ws.rs.core.Response;

/**
 * Implements an asynchronous callback handler for REST client requests.
 * This class handles the response or failure of an HTTP asynchronous request made .
 */
public class Callback implements InvocationCallback<Response> {
    // Stores the response message received from the server
	private String messageFromTheServer;

    /**
     * Called when the asynchronous request completes successfully.
     * Reads the response body as a String if the HTTP status is 200 OK,
     * otherwise logs an error with the status code.
     * @param response the HTTP response received
     */
	@Override
	public void completed(Response response) {
		if (response.getStatus() == 200) {
			messageFromTheServer = response.readEntity(String.class);
		} else {
			System.err.println("Request error: " + response.getStatus());
		}
	}

	/**
     * Called when the asynchronous request fails.
     * Prints the stack trace of the throwable cause.
     *
     * @param throwable the error thrown during the request
     */
	@Override
	public void failed(Throwable throwable) {
		throwable.printStackTrace();
	}

    /**
     * Returns the message received from the server after a successful response.
     *
     * @return the response message as a String, or null if none received
     */
	public String getResponse() {
		return messageFromTheServer;
	}
}
