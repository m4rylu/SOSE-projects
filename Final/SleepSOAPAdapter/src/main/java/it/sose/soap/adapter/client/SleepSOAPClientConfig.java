package it.sose.soap.adapter.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import it.sose.soap.sleep.SleepTrackerPort;
import it.sose.soap.sleep.SleepTrackerPortService;
import jakarta.xml.ws.BindingProvider;

@Configuration
public class SleepSOAPClientConfig {


    @Bean
    public SleepTrackerPort sleepTrackerClient() {
        SleepTrackerPortService service = new SleepTrackerPortService();
        SleepTrackerPort port = service.getSleepTrackerPortSoap11();
        
        ((BindingProvider) port).getRequestContext().put(
                BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                "http://sleep-service:8082/services/sleepTracker"
            );
        
        return port;
    }
}

