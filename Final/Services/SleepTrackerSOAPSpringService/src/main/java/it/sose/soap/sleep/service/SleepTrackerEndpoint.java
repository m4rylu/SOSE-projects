
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

/**
 * SOAP Web Service endpoint that handles incoming requests related to SleepTracker.
 * Uses Spring WS annotations to map SOAP messages to handler methods.
 */
@Endpoint
public class SleepTrackerEndpoint {
	
	// The target namespace of the SOAP service as defined in the XSD/WSDL
	private static final String NAMESPACE_URI = "http://sleep.soap.sose.it";
	private SleepTrackerServices sleepTrackerServices;

    /**
     * Constructor with dependency injection of the SleepTrackerServices service class.
     * @param sleepTrackerServices service layer handling the business logic
     */
	@Autowired
	public SleepTrackerEndpoint(SleepTrackerServices sleepTrackerServices) {
		this.sleepTrackerServices = sleepTrackerServices;
	}
	
    /**
     * Handles the "printRequest" SOAP message.
     * 
     * @param request the incoming PrintRequest payload
     * @return PrintResponse containing the current state of the sleep tracker as a string
     */
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "printRequest")
	@ResponsePayload
	public PrintResponse print(@RequestPayload PrintRequest request) {
		PrintResponse response = new PrintResponse();
		response.setReturn(sleepTrackerServices.printSleepTracker());

		return response;
	}
	
    /**
     * Handles the "addRequest" SOAP message.
     * 
     * @param request the incoming AddRequest containing the integer value to add
     * @return AddResponse with a confirmation message after adding the value
     */
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addRequest")
	@ResponsePayload
	public AddResponse add(@RequestPayload AddRequest request) {
		AddResponse response = new AddResponse();
		int arg0 = request.getArg0();
		response.setReturn(sleepTrackerServices.addSleepTracker(arg0));

		return response;
	}
	
    /**
     * Handles the "lastValuesRequest" SOAP message.
     * 
     * @param request the incoming LastValuesRequest payload
     * @return LastValuesResponse containing the sleep tracker data of the last 7 days as a string
     */
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "lastValuesRequest")
	@ResponsePayload
	public LastValuesResponse lastValues(@RequestPayload LastValuesRequest request) {
		LastValuesResponse response = new LastValuesResponse();
		response.setReturn(sleepTrackerServices.last7DaysValues());

		return response;
	}
}
