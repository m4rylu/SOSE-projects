package it.sose.advice.coffee;

import java.util.concurrent.Future;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Response;

//ho utilizzato l'approccio della callback per le prestazioni migliori anche se piu omplicato da gestire.

public class AdviceCoffeeImpl implements AdviceCoffee{
	
	@Override
	public String getAdviceCoffee() {
		String advice;
		int coffeeSum=0;
		int sleepSum=0;
		int[] coffeeArray;
		int[] sleepArray;
		
		Client client = ClientBuilder.newClient();
		
		Callback coffeeHandler = new Callback();
		Callback sleepHandler = new Callback();
		// we execute services in parallel (asynchronously)
		Future<Response> futureCoffeeResponse = client.target("http://localhost:8080/WeatherTrackerRESTSpring/lastValues").request().async().get(coffeeHandler);
		Future<Response> futureSleepResponse = client.target("http://localhost:8080/WeatherTrackerRESTSpring/lastValues").request().async().get(sleepHandler);
		while(!futureCoffeeResponse.isDone() || !futureSleepResponse.isDone()) {
			//The prosumer wait for both the result syncronizing the 2 processes
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		String coffeeResponse = coffeeHandler.getResponse();
		String sleepResponse = sleepHandler.getResponse();
		
	    if (coffeeResponse == null || sleepResponse == null) {
	        return "Errore nel recuperare i dati dai servizi.";
	    }
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			coffeeArray = mapper.readValue(coffeeResponse, int[].class);
			sleepArray = mapper.readValue(sleepResponse, int[].class);
			
			coffeeSum+=coffeeArray[1];
			for(int i=1; i<8 ; i+=2) {
				coffeeSum+=coffeeArray[i];
			}
			
			sleepSum+=sleepArray[1];
			for(int i=1; i<8 ; i+=2) {
				sleepSum+=sleepArray[i];
			}
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(coffeeSum<=35 && sleepSum>=56 ) {
			advice="Bravo! Hai bevuto la quantità consigliata di caffè e dormito.";
		} else if(coffeeSum>35 && sleepSum>=56) {
			advice="Hai bevuto troppi caffè! ma hai dormito il giusto :)";
		} else if(coffeeSum>35 && sleepSum<56) {
			advice="mi sembri un pò stressato...hai preso troppi caffè e dormito poco :/";
		} else {
			advice="hai preso il giusto numero di caffè! però hai dormito poco";
		}
		
		return advice;
	}
}
