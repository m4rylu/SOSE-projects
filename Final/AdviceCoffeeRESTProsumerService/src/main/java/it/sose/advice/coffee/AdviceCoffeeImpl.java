package it.sose.advice.coffee;

import java.util.concurrent.Future;
import it.sose.soap.sleep.SleepTrackerPort;
import it.sose.soap.sleep.SleepTrackerPortService;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Response;
import it.sose.soap.sleep.Last7DaysValuesRequest;
import it.sose.soap.sleep.Last7DaysValuesResponse;


//ho utilizzato l'approccio della callback per prestazioni migliori anche se piu omplicato da gestire.

public class AdviceCoffeeImpl implements AdviceCoffee{
	
	@Override
	public String getAdviceCoffee() {
		
		char c;
		char c1;
		int num=0;
		int coffeeSum = 0;
		int sleepSum = 0;
		String advice;
		
		// client for coffee service REST
		Client client = ClientBuilder.newClient();
		Callback coffeeHandler = new Callback();
		
		Future<Response> futureCoffeeResponse = client.target("http://localhost:8080/CoffeeTrackerRESTServiceMaven/coffee/lastValues").request().async().get(coffeeHandler);
		
		//client for sleep service SOAP
		SleepTrackerPortService service = new SleepTrackerPortService();
		SleepTrackerPort endpoint = service.getSleepTrackerPortSoap11();
		Last7DaysValuesRequest request = new Last7DaysValuesRequest();
		SOAPAsynchHandler sleepHandler = new SOAPAsynchHandler();
		
		Future<?> futureSleepResponse = endpoint.last7DaysValuesAsync(request, sleepHandler);
		
		while(!futureCoffeeResponse.isDone() || !futureSleepResponse.isDone()) {
			//The prosumer wait for both the result syncronizing the 2 processes
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		

		String coffeeResponse = coffeeHandler.getResponse();
		String sleepResponse = sleepHandler.getResponse().getReturn();
	    if (coffeeResponse == null || sleepResponse == null) {
	        return "Errore nel recuperare i dati dai servizi.";
	    }
	    
	    for(int i = 1; i < coffeeResponse.length()-1; i+=2) {
	    	c = coffeeResponse.charAt(i);
	    	num= Character.getNumericValue(c);
	    	coffeeSum+=num;
	    }
	    
	    for(int i = 1; i < sleepResponse.length()-1; i++) {
	    	c = sleepResponse.charAt(i);
	    	c1 = sleepResponse.charAt(i++);
	    	
	    	if (c1==(',')) {
	    		num= Character.getNumericValue(c);
	    		sleepSum+=num;
	    	} else {
	    		num = Integer.parseInt(Character.toString(c) + Character.toString(c1));
	    		sleepSum+=num;
	    		i+=1;
	    	}
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



