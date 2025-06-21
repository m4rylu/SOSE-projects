package it.sose.rest.weather.service;

public interface WeatherTracker {
	String printWeatherTracker();
	String addWeatherTracker(int a);
	int[] last7DaysValue ();
}
