package it.sose.soap.adapter.service;

import org.springframework.stereotype.Service;

import it.sose.advice.mood.AdviceMood;
import it.sose.advice.mood.ExecutionException_Exception;
import it.sose.advice.mood.InterruptedException_Exception;

@Service
public class AdviceMoodSOAPAdapterService {
	private final AdviceMood soapClient;
	
	public AdviceMoodSOAPAdapterService(AdviceMood soapClient) {
		this.soapClient = soapClient;
	}
	
	public String adviceMood() throws ExecutionException_Exception, InterruptedException_Exception {
		return soapClient.advice();
	}
}

