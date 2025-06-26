package it.sose.soap.umor;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public interface UmorTracker {

	@WebMethod(operationName = "print")
	public String printUmorTracker();
	
	@WebMethod(operationName = "add")
	public String addUmorTracker(int humor);
	
	@WebMethod(operationName= "lastValues")
	public int[] last7DaysValues();
}
