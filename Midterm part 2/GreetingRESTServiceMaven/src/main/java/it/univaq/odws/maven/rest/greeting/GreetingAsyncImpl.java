package it.univaq.odws.maven.rest.greeting;

import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.ws.rs.container.AsyncResponse;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class GreetingAsyncImpl implements GreetingAsync {
	private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
  
	@Override
	public void sayHello(String name, AsyncResponse asyncResponse) throws Exception {
		System.out.println(formatter.format(new Date())
				+ " - executing public void sayHello(String name, "
				+ "AsyncResponse asyncResponse) throws Exception");
		new Thread() {
			public void run() {
				try {
					Thread.sleep(10000); // 10s

					Response response = Response.ok("hello " + name)
							                           .type(MediaType.TEXT_PLAIN).build();
					System.out.println(formatter.format(new Date()) 
							                       + " - responding on background thread");
					asyncResponse.resume(response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
}
