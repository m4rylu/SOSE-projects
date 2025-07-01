package it.sose.advice.mood;

import java.util.concurrent.ExecutionException;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public interface AdviceMood {
	
	@WebMethod(operationName = "advice")
	public String adviceMood() throws InterruptedException, ExecutionException;
}
