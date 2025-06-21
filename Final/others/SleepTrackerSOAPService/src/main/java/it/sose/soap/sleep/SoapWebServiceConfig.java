package it.sose.soap.sleep;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.xml.ws.Endpoint;

@Configuration
public class SoapWebServiceConfig {

    @Bean
    public Endpoint endpoint(Bus bus, SleepTrackerImpl serviceImpl) {
        EndpointImpl endpoint = new EndpointImpl(bus, serviceImpl);
        endpoint.publish("/sleepTracker"); // URL finale
        return endpoint;
    }
}

