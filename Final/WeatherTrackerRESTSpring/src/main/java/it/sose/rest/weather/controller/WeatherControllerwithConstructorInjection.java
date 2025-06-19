package it.sose.rest.weather.controller;

import it.sose.rest.weather.service.WeatherTracker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/weatherTracker")
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
    
    @GetMapping("/add")
    public String add(@RequestParam int a) {
        return weatherTracker.addWeatherTracker(a);
    }
}