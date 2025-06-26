package it.sose.advice.coffee;

import it.sose.soap.sleep.Last7DaysValuesResponse;
import jakarta.xml.ws.AsyncHandler;
import jakarta.xml.ws.Response;

public class SOAPAsynchHandler implements AsyncHandler<Last7DaysValuesResponse> {

	private Last7DaysValuesResponse last7DaysValuesResponse;

	@Override
	public void handleResponse(Response<Last7DaysValuesResponse> response) {
		try {
			last7DaysValuesResponse = response.get();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Last7DaysValuesResponse getResponse() {
		return last7DaysValuesResponse;
	}
}