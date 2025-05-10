package it.univaq.odws.maven.rest.greeting;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.container.AsyncResponse;
import jakarta.ws.rs.container.Suspended;

@Path("/greetingrestserviceasync")
public interface GreetingAsync {
	
	@GET
	@Path("/say_hello/{name}")
	void sayHello(@PathParam("name") final String name, 
			      @Suspended final AsyncResponse asyncResponse)throws Exception;

}
