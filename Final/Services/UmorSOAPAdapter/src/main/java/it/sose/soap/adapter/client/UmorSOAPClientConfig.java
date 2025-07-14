package it.sose.soap.adapter.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import it.sose.soap.umor.UmorTracker;
import it.sose.soap.umor.UmorTrackerImplService;
import jakarta.xml.ws.BindingProvider;

/**
 * Configuration class responsible for creating and configuring the SOAP client.
 * This class is annotated with @Configuration because contains Spring-managed definitions
 */
@Configuration
public class UmorSOAPClientConfig {

	/*
	 * Creates and configures a SOAP client for the UmorTracker interface
	 * @return a configurated UmorTracker SOAP client.
	 */
    @Bean
    public UmorTracker umorTrackerClient() {
        UmorTrackerImplService service = new UmorTrackerImplService();
        UmorTracker port = service.getUmorTrackerImplPort();
        
		// Override endpoint address (because we're deploying on Docker)
        ((BindingProvider) port).getRequestContext().put(
                BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                "http://humor-service:8085/UmorTrackerSOAPServiceMaven/umor"
            );
        
        return port;
    }
}
