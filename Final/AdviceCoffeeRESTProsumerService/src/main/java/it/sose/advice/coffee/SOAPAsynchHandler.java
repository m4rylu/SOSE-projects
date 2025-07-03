package it.sose.advice.coffee;

import it.sose.soap.sleep.LastValuesResponse;
import jakarta.xml.ws.AsyncHandler;
import jakarta.xml.ws.Response;

public class SOAPAsynchHandler implements AsyncHandler<LastValuesResponse> {

	private LastValuesResponse lastValuesResponse;

	@Override
	public void handleResponse(Response<LastValuesResponse> response) {
		try {
			lastValuesResponse = response.get();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public LastValuesResponse getResponse() {
		return lastValuesResponse;
	}
}