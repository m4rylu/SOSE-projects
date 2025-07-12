package it.sose.soap.sleep.service;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

/**
 * Configuration class to enable and configure SOAP Web Services
 * using Spring Web Services
 */
@EnableWs // Enables SOAP Web Services support in Spring Boot
@Configuration
public class WebServiceConfig {

	/**
     * Registers the MessageDispatcherServlet (Spring-WS Servlet) for handling SOAP requests.
     * The servlet intercepts requests at the "/services/*" URL path.
     *
     * @param applicationContext the Spring application context
     * @return ServletRegistrationBean configured for the MessageDispatcherServlet
     */
	@Bean
	public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(
			ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		// Set the application context for the servlet to detect Spring beans
		servlet.setApplicationContext(applicationContext);
		// Enables WSDL URL transformation to make WSDL accessible correctly
		servlet.setTransformWsdlLocations(true);
		// Map the servlet to "/services/*"
		return new ServletRegistrationBean<>(servlet, "/services/*");
	}

	/**
     * Defines the WSDL 1.1 definition bean named "sleepTracker".
     * This bean exposes the WSDL based on the given XSD schema.
     *
     * @param sleepSchema the XSD schema bean representing the XML schema for sleepTracker
     * @return DefaultWsdl11Definition configured with port type, location URI, namespace, and schema
     */
	@Bean(name = "sleepTracker")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema sleepSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		// Set the name of the portType in the WSDL (SOAP interface)
		wsdl11Definition.setPortTypeName("sleepTrackerPort");
		// Set the base URI where the service listens
		wsdl11Definition.setLocationUri("/services");
		// Set the target namespace of the WSDL
		wsdl11Definition.setTargetNamespace("http://sleep.soap.sose.it");
		// Set the XML schema for message validation and structure
		wsdl11Definition.setSchema(sleepSchema);
		return wsdl11Definition;
	}

	/**
     * Loads the sleepTracker XML schema from the classpath.
     * This schema defines the structure of the SOAP messages.
     *
     * @return XsdSchema representing the sleepTracker.xsd
     */
	@Bean
	public XsdSchema sleepSchema() {
		return new SimpleXsdSchema(new ClassPathResource("sleepTracker.xsd"));
	}
}