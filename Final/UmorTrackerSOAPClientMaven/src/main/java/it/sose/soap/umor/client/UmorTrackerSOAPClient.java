package it.sose.soap.umor.client;

import it.sose.soap.umor.UmorTracker;
import it.sose.soap.umor.UmorTrackerImplService;

public class UmorTrackerSOAPClient {

	public static void main(String[] args) {
		UmorTrackerImplService service = new UmorTrackerImplService();
		UmorTracker port = service.getUmorTrackerImplPort();
		String response = port.add(5);
		 System.out.println("response is:"+response);
	}
}
