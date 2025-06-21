package it.sose.soap.sleep;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;

@WebService
public interface SleepTracker {

	@WebMethod
	String printSleepTracker();
	
	@WebMethod
	String addSleepTracker(int a);
}
