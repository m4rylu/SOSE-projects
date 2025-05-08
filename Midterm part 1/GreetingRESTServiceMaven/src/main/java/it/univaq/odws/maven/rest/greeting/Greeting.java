package it.univaq.odws.maven.rest.greeting;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/greetingrestservice")
public interface Greeting {
	
	@GET
	@Path("greeting_text_plain/{name}")
	@Produces({MediaType.TEXT_PLAIN})
	String greetingTextPlain (@PathParam("name") String name);
	
	@GET
	@Path("greeting_text_html/{name}")
	@Produces({MediaType.TEXT_HTML})
	String greetingTextHtml (@PathParam("name") String name);
	
	@GET
	@Path("greeting_text_xml/{name}")
	@Produces({MediaType.TEXT_XML})
	String greetingTextXml (@PathParam("name") String name);
}
