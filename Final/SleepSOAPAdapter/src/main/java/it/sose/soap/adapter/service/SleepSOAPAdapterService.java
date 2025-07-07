package it.sose.soap.adapter.service;

import org.springframework.stereotype.Service;

import it.sose.soap.sleep.AddRequest;
import it.sose.soap.sleep.LastValuesRequest;
import it.sose.soap.sleep.PrintRequest;
import it.sose.soap.sleep.SleepTrackerPort;

@Service
public class SleepSOAPAdapterService {
	private final SleepTrackerPort soapClient;
	
	public SleepSOAPAdapterService(SleepTrackerPort soapClient) {
		this.soapClient = soapClient;
	}
	
	public String print() {
		PrintRequest request = new PrintRequest(); 
		return soapClient.print(request).getReturn();
	}
	
	public String add(int a) {
		AddRequest request = new AddRequest();
		request.setArg0(a);
		return soapClient.add(request).getReturn();
	}
	
	public String lastValues() {
		LastValuesRequest request = new LastValuesRequest();
		return soapClient.lastValues(request).getReturn();
	}
}
