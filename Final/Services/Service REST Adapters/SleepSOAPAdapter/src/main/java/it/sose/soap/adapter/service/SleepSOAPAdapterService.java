package it.sose.soap.adapter.service;

import org.springframework.stereotype.Service;

import it.sose.soap.sleep.AddRequest;
import it.sose.soap.sleep.LastValuesRequest;
import it.sose.soap.sleep.PrintRequest;
import it.sose.soap.sleep.SleepTrackerPort;
/**
 * Service class that acts as an adapter between the REST controller and the SOAP-based SleepTracker service.
 */
@Service
public class SleepSOAPAdapterService {
	
    /**
     * Reference to the SOAP client that implements the SleepTracker interface.
     * This client is used to invoke the underlying SOAP operation.
     */
	private final SleepTrackerPort soapClient;
	
	
    /**
     * Constructor-based dependency injection of the SOAP client.
     * Spring will inject the correct implementation at runtime.
     *
     * @param soapClient the SOAP-based service that provides sleep tracker services.
     */
	public SleepSOAPAdapterService(SleepTrackerPort soapClient) {
		this.soapClient = soapClient;
	}
	
	/**
     * Calls the underlying SOAP operation to retrieve sleep tracker data.
     * @return a string containing the data
     */
	public String print() {
		PrintRequest request = new PrintRequest(); 
		return soapClient.print(request).getReturn();
	}
	
	/**
     * Calls the underlying SOAP operation to add data to the sleep tracker.
     * @return a string confirmation message or an error message if input is invalid
     */
	public String add(int a) {
		AddRequest request = new AddRequest();
		request.setArg0(a);
		return soapClient.add(request).getReturn();
	}
	
	/**
     * Calls the underlying SOAP operation to print last 7 days data of the sleep tracker.
     * @return a string representation (e.g. array format) of the last 7 days' values
     */
	public String lastValues() {
		LastValuesRequest request = new LastValuesRequest();
		return soapClient.lastValues(request).getReturn();
	}
}
