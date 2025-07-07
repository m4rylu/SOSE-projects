package it.sose.soap.adapter.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import it.sose.advice.mood.AdviceMood;
import it.sose.advice.mood.AdviceMoodImplService;
import jakarta.xml.ws.BindingProvider;

@Configuration
public class AdviceMoodSOAPAdapterClientConfig {

    @Bean
    public AdviceMood AdviceMoodClient() {
        AdviceMoodImplService service = new AdviceMoodImplService();
        AdviceMood port = service.getAdviceMoodImplPort();
        
        ((BindingProvider) port).getRequestContext().put(
                BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                "http://advice-mood-service:8083/AdviceMoodSOAPProsumerServiceMaven/moodAdvice"
            );
        
        return port;
    }
}