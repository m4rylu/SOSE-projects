package it.sose.advice.mood;

import java.util.concurrent.ExecutionException;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

/**
 * This interface defines the operations that the SOAP web service expones.
 * It is annotated with @WebService to mark it as a JAX-WS service endpoint.
 */
@WebService
public interface AdviceMood {
	
	/**
	 * Exposes a SOAP operation named "advice".
	 * Returns a personalized advice for the user, based on their recorded data
     * over the last 7 days, specifically their daily ratings and mood (humor).
     * @return a text advice for the users based on saved data.insight based on the user's last 7 days of input.
     */
	@WebMethod(operationName = "advice")
	public String adviceMood() throws InterruptedException, ExecutionException;
}
