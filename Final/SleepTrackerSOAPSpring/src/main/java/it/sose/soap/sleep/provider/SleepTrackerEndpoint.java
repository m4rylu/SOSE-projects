package it.sose.soap.sleep.provider;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import it.sose.soap.sleep.AddSleepTracker;
import it.sose.soap.sleep.AddSleepTrackerResponse;
import it.sose.soap.sleep.ObjectFactory;
import it.sose.soap.sleep.PrintSleepTracker;
import it.sose.soap.sleep.PrintSleepTrackerResponse;

@Endpoint
public class DreamTrackerEndpoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(DreamTrackerEndpoint.class);

	@PayloadRoot(namespace = "http:/sleep.soap.sose.it", localPart = "printSleepTracker")
	@ResponsePayload
	public PrintSleepTrackerResponse printSleepTracker(@RequestPayload PrintSleepTracker request) {

		LOGGER.info("**** 'SleepTrackerEndpoint' RECEIVED A REQUEST FOR 'printSleepTracker()'");

		ObjectFactory factory = new ObjectFactory();
		PrintSleepTrackerResponse response = factory.createPrintSleepTrackerResponse();

		LOGGER.info("**** 'SleepTrackerEndpoint' IS GOING TO SEND THE RESULT OF THE 'printSleepTracker()' OPERATION ='{}'",
				response.getReturn());

		return response;
	}
	
	@PayloadRoot(namespace = "http:/sleep.soap.sose.it", localPart = "addSleepTracker")
	@ResponsePayload
	public AddSleepTrackerResponse addSleepTracker(@RequestPayload AddSleepTracker request) {
		LOGGER.info("**** 'SumEndpoint' RECEIVED A REQUEST FOR 'noSafeSum(arg0={})'", request.getArg0());

		ObjectFactory factory = new ObjectFactory();
		AddSleepTrackerResponse response = factory.createAddSleepTrackerResponse();

		LOGGER.info("**** 'SleepTrackerEndpoint' IS GOING TO SEND THE RESULT OF THE 'addSleepTracker()' OPERATION ='{}'",
				response.getReturn());

		return response;
	}
}
