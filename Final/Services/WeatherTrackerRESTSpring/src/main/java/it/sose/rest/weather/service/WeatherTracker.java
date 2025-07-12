package it.sose.rest.weather.service;

/**
 * Interface for tracking and retrieving daily weather-related values.
 * Implementations must handle storage, retrieval, and formatting of the data.
 */
public interface WeatherTracker{
	
	/**
     * Returns a formatted string representing all stored weather values
     * for each day of the year, organized by month and day.
     * @return a human-readable table of recorded weather values
     */
	String printWeatherTracker();
	
    /**
     * Records a new weather value for the current day.
     * @param a the weather value to record for today
     * @return a confirmation message or an error message if input is invalid
     */
	String addWeatherTracker(int a);
	
    /**
     * Retrieves the recorded weather values for the past 7 days (excluding today).
     * @return a string representation (e.g. array format) of the last 7 days' values
     */
	String last7DaysValues ();
}
