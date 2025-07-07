package it.sose.rest.weather.controller;

import it.sose.rest.weather.service.WeatherTracker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/WeatherTrackerRESTSpring")
public class WeatherControllerwithConstructorInjection {

	// **** For the Constructor Injection this can be declared final ****
	private final WeatherTracker weatherTracker;

    // **** Constructor Injection ****
    public WeatherControllerwithConstructorInjection(WeatherTracker weatherTracker) {
        this.weatherTracker = weatherTracker;
    }
    
    @GetMapping("/print")
    public String print() {
        return weatherTracker.printWeatherTracker();
    }
    
    @GetMapping("/add/{a}")
    public String add(@PathVariable int a) {
        return weatherTracker.addWeatherTracker(a);
    }
    
    @GetMapping("/lastValues")
    public String lastValues() {
    	return weatherTracker.last7DaysValues();
    }
    
}