package it.sose.advice.mood;

import jakarta.servlet.ServletConfig;
import jakarta.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.transport.servlet.CXFNonSpringServlet;

/**
 * Custom servlet that initializes a CXF endpoint without using Spring.
 */
public class SimpleCXFNonSpringServlet extends CXFNonSpringServlet {

	private static final long serialVersionUID = -6420470370401020050L;

	/**
	 * Called automatically when the servlet is initialized.
	 * It sets up the CXF Bus and publishes a SOAP service endpoint.
	 * @param servletConfig it contains the configuration of the servlet, defined in the web.xml
	 */
	@Override
	public void loadBus(ServletConfig servletConfig) {
		
		// Call superclass to initialize the bus and configuration
		super.loadBus(servletConfig);
		
		// Retrieve the initialized CXF bus (communication context)
		Bus bus = getBus();
		
		// Set as default for the current thread context
		BusFactory.setDefaultBus(bus);
		
		// Publish the SOAP service at the relative path "/umor"
		Endpoint.publish("/moodAdvice", new AdviceMoodImpl());
	}
}
