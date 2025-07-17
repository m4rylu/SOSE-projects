package it.sose.soap.adapter.service;

import org.springframework.stereotype.Service;

import it.sose.soap.umor.UmorTracker;

/**
 * Service class that acts as an adapter between the REST controller and the SOAP-based UmorTracker service.
 */
@Service
public class UmorSOAPAdapterService {
	
    /**
     * Reference to the SOAP client that implements the UmorTracker interface.
     * This client is used to invoke the underlying SOAP operation.
     */
	private final UmorTracker soapClient;
	
    /**
     * Constructor-based dependency injection of the SOAP client.
     * Spring will inject the correct implementation at runtime.
     *
     * @param soapClient the SOAP-based service that provides humor tracker services.
     */
	public UmorSOAPAdapterService(UmorTracker soapClient) {
		this.soapClient = soapClient;
	}
	
	/**
     * Calls the underlying SOAP operation to retrieve humor tracker data.
     * @return a string containing the data
     */
	public String print() {
		return soapClient.print();
	}
	
	/**
     * Calls the underlying SOAP operation to add data to the humor tracker.
     * @param a value indicating today's mood
     * @return a string confirmation message or an error message if input is invalid
     */
	public String add(int a) {
		return soapClient.add(a);
	}
	
	/**
     * Calls the underlying SOAP operation to print last 7 days data of the humor tracker.
     * @return a string representation of the last 7 days' values
     */
	public String lastValues() {
		return soapClient.lastValues();
	}
}
