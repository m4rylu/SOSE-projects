package it.sose.soap.sleep.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import it.sose.soap.sleep.AddSleepTrackerRequest;
import it.sose.soap.sleep.AddSleepTrackerResponse;
import it.sose.soap.sleep.Last7DaysValuesRequest;
import it.sose.soap.sleep.Last7DaysValuesResponse;
import it.sose.soap.sleep.PrintSleepTrackerRequest;
import it.sose.soap.sleep.PrintSleepTrackerResponse;


@Endpoint
public class SleepTrackerEndpoint {
	private static final String NAMESPACE_URI = "http://sleep.soap.sose.it";
	private SleepTrackerServices sleepTrackerServices;

	@Autowired
	public SleepTrackerEndpoint(SleepTrackerServices sleepTrackerServices) {
		this.sleepTrackerServices = sleepTrackerServices;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "printSleepTrackerRequest")
	@ResponsePayload
	public PrintSleepTrackerResponse printSleepTracker(@RequestPayload PrintSleepTrackerRequest request) {
		PrintSleepTrackerResponse response = new PrintSleepTrackerResponse();
		response.setReturn(sleepTrackerServices.printSleepTracker());

		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addSleepTrackerRequest")
	@ResponsePayload
	public AddSleepTrackerResponse addSleepTracker(@RequestPayload AddSleepTrackerRequest request) {
		AddSleepTrackerResponse response = new AddSleepTrackerResponse();
		int arg0 = request.getArg0();
		response.setReturn(sleepTrackerServices.addSleepTracker(arg0));

		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "last7DaysValuesRequest")
	@ResponsePayload
	public Last7DaysValuesResponse last7DaysValues(@RequestPayload Last7DaysValuesRequest request) {
		Last7DaysValuesResponse response = new Last7DaysValuesResponse();
		response.setReturn(sleepTrackerServices.last7DaysValues());

		return response;
	}
}
