package it.sose.soap.adapter.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import it.sose.advice.mood.AdviceMood;
import it.sose.advice.mood.AdviceMoodImplService;
import jakarta.xml.ws.BindingProvider;

/**
 * Configuration class responsible for creating and configuring the SOAP client.
 * This class is annotated with @Configuration because contains Spring-managed definitions
 */
@Configuration
public class AdviceMoodSOAPAdapterClientConfig {

	/*
	 * Creates and configures a SOAP client for the AdviceMood interface
	 * @return a configurated AdviceMood SOAP client.
	 */
    @Bean
    public AdviceMood AdviceMoodClient() {
        AdviceMoodImplService service = new AdviceMoodImplService();
        AdviceMood port = service.getAdviceMoodImplPort();
        
		// Override endpoint address (because we're deploying on Docker)
        ((BindingProvider) port).getRequestContext().put(
                BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                "http://advice-mood-service:8083/AdviceMoodSOAPProsumerServiceMaven/moodAdvice"
            );
        
        return port;
    }
}