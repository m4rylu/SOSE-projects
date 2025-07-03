package it.sose.soap.adapter.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import it.sose.soap.umor.UmorTracker;
import it.sose.soap.umor.UmorTrackerImplService;
import jakarta.xml.ws.BindingProvider;

@Configuration
public class UmorSOAPClientConfig {

    @Bean
    public UmorTracker umorTrackerClient() {
        UmorTrackerImplService service = new UmorTrackerImplService();
        UmorTracker port = service.getUmorTrackerImplPort();
        
        ((BindingProvider) port).getRequestContext().put(
                BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                "http://humor-service:8085/UmorTrackerSOAPServiceMaven/umor"
            );
        
        return port;
    }
}
