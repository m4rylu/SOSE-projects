package it.sose.soap.umor;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public interface UmorTracker {

	@WebMethod
	public String printUmorTracker();
	
	@WebMethod
	public String addUmorTracker(int humor);
}
