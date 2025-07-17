package it.sose.soap.adapter.service;

import org.springframework.stereotype.Service;

import it.sose.advice.mood.AdviceMood;
import it.sose.advice.mood.ExecutionException_Exception;
import it.sose.advice.mood.InterruptedException_Exception;
/**
 * Service class that acts as an adapter between the REST controller and the SOAP-based AdviceMood service.
 */
@Service
public class AdviceMoodSOAPAdapterService {
    /**
     * Reference to the SOAP client that implements the AdviceMood interface.
     * This client is used to invoke the underlying SOAP operation.
     */
	private final AdviceMood soapClient;
	
    /**
     * Constructor-based dependency injection of the SOAP client.
     * Spring will inject the correct implementation at runtime.
     *
     * @param soapClient the SOAP-based service that provides mood advice.
     */
	public AdviceMoodSOAPAdapterService(AdviceMood soapClient) {
		this.soapClient = soapClient;
	}
	
	  /**
     * Calls the underlying SOAP operation to retrieve personalized mood advice.
     * @return a string containing mood advice for the user.
     * @throws ExecutionException_Exception if the SOAP service encounters an execution error.
     * @throws InterruptedException_Exception if the asynchronous SOAP request is interrupted.
     */
	public String adviceMood() throws ExecutionException_Exception, InterruptedException_Exception {
		return soapClient.advice();
	}
}

