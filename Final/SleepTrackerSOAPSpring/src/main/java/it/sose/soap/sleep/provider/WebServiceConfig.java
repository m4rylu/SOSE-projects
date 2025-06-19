package it.sose.soap.sleep.provider;

import jakarta.servlet.Servlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.Wsdl11Definition;

@EnableWs
@Configuration

//WSConfigurerAdapter is deprecated
public class WebServiceConfig  {

	@Bean
	public ServletRegistrationBean<Servlet> messageDispatcherServlet(ApplicationContext applicationContext) {
		
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);

		return new ServletRegistrationBean<>(servlet, "/sleepTracker/*");
	}

	@Bean(name = "sleepTracker")
	public Wsdl11Definition defaultWsdl11Definition() {
		
		SimpleWsdl11Definition wsdl11Definition = new SimpleWsdl11Definition();
		
		// The wsdl can be accessed at http://localhost:8080/mycourse/springws/sum_soap_spring_ws_migrated/sumRefined.wsdl
		wsdl11Definition.setWsdl(new ClassPathResource("/wsdl/SleepService.wsdl"));

		return wsdl11Definition;
	}
}