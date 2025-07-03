package it.sose.soap.sleep.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import it.sose.soap.sleep.AddRequest;
import it.sose.soap.sleep.AddResponse;
import it.sose.soap.sleep.LastValuesRequest;
import it.sose.soap.sleep.LastValuesResponse;
import it.sose.soap.sleep.PrintRequest;
import it.sose.soap.sleep.PrintResponse;


@Endpoint
public class SleepTrackerEndpoint {
	private static final String NAMESPACE_URI = "http://sleep.soap.sose.it";
	private SleepTrackerServices sleepTrackerServices;

	@Autowired
	public SleepTrackerEndpoint(SleepTrackerServices sleepTrackerServices) {
		this.sleepTrackerServices = sleepTrackerServices;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "printRequest")
	@ResponsePayload
	public PrintResponse print(@RequestPayload PrintRequest request) {
		PrintResponse response = new PrintResponse();
		response.setReturn(sleepTrackerServices.printSleepTracker());

		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addRequest")
	@ResponsePayload
	public AddResponse add(@RequestPayload AddRequest request) {
		AddResponse response = new AddResponse();
		int arg0 = request.getArg0();
		response.setReturn(sleepTrackerServices.addSleepTracker(arg0));

		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "lastValuesRequest")
	@ResponsePayload
	public LastValuesResponse lastValues(@RequestPayload LastValuesRequest request) {
		LastValuesResponse response = new LastValuesResponse();
		response.setReturn(sleepTrackerServices.last7DaysValues());

		return response;
	}
}
