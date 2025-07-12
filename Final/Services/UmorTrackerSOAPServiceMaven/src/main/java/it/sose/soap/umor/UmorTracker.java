package it.sose.soap.umor;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

/**
 * This interface defines the operations that the SOAP web service expones.
 * It is annotated with @WebService to mark it as a JAX-WS service endpoint.
 */
@WebService
public interface UmorTracker {

	/**
	 * Exposes a SOAP operation named "print".
     * Returns a formatted string representing all stored humor values
     * for each day of the year, organized by month and day.
     * @return a human-readable table of recorded humor values
     */
	@WebMethod(operationName = "print")
	public String printUmorTracker();
	
	/**
	 * Exposes a SOAP operation named "add".
     * Records a new humor value for the current day.
     * @param humor the humor value to record for today
     * @return a confirmation message or an error message if input is invalid
     */
	@WebMethod(operationName = "add")
	public String addUmorTracker(int humor);
	
	/**
	 * Exposes a SOAP operation named "lastValues"  
     * Retrieves the recorded weather values for the past 7 days (excluding today).
     * @return a string representation of the last 7 days' values
     */
	@WebMethod(operationName= "lastValues")
	public String last7DaysValues();
}
