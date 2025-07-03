package it.sose.soap.adapter.service;

import org.springframework.stereotype.Service;

import it.sose.soap.umor.UmorTracker;

@Service
public class UmorSOAPAdapterService {
	private final UmorTracker soapClient;
	
	public UmorSOAPAdapterService(UmorTracker soapClient) {
		this.soapClient = soapClient;
	}
	
	public String print() {
		return soapClient.print();
	}
	
	public String add(int a) {
		return soapClient.add(a);
	}
	
	public String lastValues() {
		return soapClient.lastValues();
	}
}
