package it.sose.soap.adapter.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import it.sose.soap.sleep.SleepTrackerPort;
import it.sose.soap.sleep.SleepTrackerPortService;
import jakarta.xml.ws.BindingProvider;

/**
 * Configuration class responsible for creating and configuring the SOAP client.
 * This class is annotated with @Configuration because contains Spring-managed definitions
 */
@Configuration
public class SleepSOAPClientConfig {

	/*
	 * Creates and configures a SOAP client for the SleepTracker interface
	 * @return a configurated SleepTracker SOAP client.
	 */
    @Bean
    public SleepTrackerPort sleepTrackerClient() {
        SleepTrackerPortService service = new SleepTrackerPortService();
        SleepTrackerPort port = service.getSleepTrackerPortSoap11();
        
		// Override endpoint address (because we're deploying on Docker)
        ((BindingProvider) port).getRequestContext().put(
                BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                "http://sleep-service:8082/services/sleepTracker"
            );
        
        return port;
    }
}

