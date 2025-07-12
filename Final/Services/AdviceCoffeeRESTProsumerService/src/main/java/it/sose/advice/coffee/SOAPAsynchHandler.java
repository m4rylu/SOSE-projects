package it.sose.advice.coffee;

import it.sose.soap.sleep.LastValuesResponse;
import jakarta.xml.ws.AsyncHandler;
import jakarta.xml.ws.Response;

/**
 * This class implements the AsyncHandler interface to process
 * SOAP responses of type LastValuesResponse asynchronously. 
 * The stored response can then be accessed via the getResponse() method.
 */
public class SOAPAsynchHandler implements AsyncHandler<LastValuesResponse> {
    // Stores the SOAP response received asynchronously
	private LastValuesResponse lastValuesResponse;

	/**
     * Called automatically when the asynchronous SOAP response is ready.
     * Retrieves the response from the Response object and stores it.
     * If an error occurs while getting the response, the stack trace is printed.
     * 
     * @param response the asynchronous response
     */
	@Override
	public void handleResponse(Response<LastValuesResponse> response) {
		try {
			lastValuesResponse = response.get();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    /**
     * Returns the stored SOAP response.
     * 
     * @return the LastValuesResponse received asynchronously,
     *         or null if not yet available or an error occurred.
     */
	public LastValuesResponse getResponse() {
		return lastValuesResponse;
	}
}