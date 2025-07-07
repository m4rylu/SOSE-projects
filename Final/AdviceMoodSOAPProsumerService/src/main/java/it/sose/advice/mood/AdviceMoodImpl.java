package it.sose.advice.mood;

import it.sose.soap.umor.UmorTracker;
import it.sose.soap.umor.UmorTrackerImplService;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import it.sose.soap.umor.LastValues;
import it.sose.soap.umor.LastValuesResponse;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.xml.ws.BindingProvider;







public class AdviceMoodImpl implements AdviceMood{
	@Override
	public String adviceMood() throws InterruptedException, ExecutionException {
		String advice;
		char c;
		int num;
		int rateSum = 0;
		int umorSum = 0;

		//client rest part
		Client client = ClientBuilder.newClient();
		Future<jakarta.ws.rs.core.Response> rateMyDayResponse = client.target("http://rate-service:8084/RateMyDayTrackerRESTServiceMaven/rate/lastValues").request().async().get();
		
		//client soap
		UmorTrackerImplService service = new UmorTrackerImplService();
		UmorTracker endpoint = service.getUmorTrackerImplPort();
		
        ((BindingProvider) endpoint).getRequestContext().put(
                BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                "http://humor-service:8085/UmorTrackerSOAPServiceMaven/umor"
            );
		
		
		LastValues request = new LastValues();
		jakarta.xml.ws.Response<LastValuesResponse> umorResponse = endpoint.lastValuesAsync(request);
		
		while( !(rateMyDayResponse.isDone() && umorResponse.isDone()) ) {
				Thread.sleep(1000);
		}
		//rest response
		String rateMyDayData = rateMyDayResponse.get().readEntity(String.class);
		
		//soap response
		String umorData = umorResponse.get().getReturn();
		
		for(int i = 1; i < rateMyDayData.length()-1; i+=2) {
	    	c = rateMyDayData.charAt(i);
	    	num = Character.getNumericValue(c);
	    	rateSum+=num;
	    }
	    
		for(int i = 1; i < umorData.length()-1; i+=2) {
	    	c = umorData.charAt(i);
	    	num = Character.getNumericValue(c);
	    	umorSum+=num;
	    }
	    	
		if(rateSum<=15 && umorSum>=20 ) {
			advice="Nonostante sono state giornate difficile il tuo umore è sempre al massimo, complimenti!";
		} else if(rateSum>15 && umorSum>=20) {
			advice="Hai avuto giornare splendide è il tuo umore è alle stelle!";
		} else if(rateSum>15 && umorSum<20) {
			advice="Nonostante hai avuto ottime giornate, qualcosa ti turba.";
		} else {
			advice="Sono state giornate orribili, è normale essere un pò tristi. Vedrai che andrà meglio C:";
		}
		
		return advice;
	}
	
}
