package it.sose.rest.advice.coffee.prosumer;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AdviceCoffeeImpl implements AdviceCoffee{
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public String getAdviceCoffee() {
		String advice;
		
		int[] dataCoffee = restTemplate.getForObject("http://localhost:8080/WeatherTrackerRESTSpring/lastValues", int[].class);
		int sum = Arrays.stream(dataCoffee).sum();
		
		if (sum<=35) {
			advice="Hai bevuto la dose di caffè consigliata, bravo!";
		} else {
			advice="Hai bevuto troppi caffè!";
		}
				
		return advice;
	}

}
