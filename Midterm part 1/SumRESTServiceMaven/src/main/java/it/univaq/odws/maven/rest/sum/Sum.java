package it.univaq.odws.maven.rest.sum;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/sumrestservice")
public interface Sum {
	
	@GET
	@Path("/sum")
	@Produces(MediaType.TEXT_PLAIN)
	int sum (@QueryParam("a") int a, 
			    @QueryParam("b") int b);

}
