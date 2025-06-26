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

@EnableWs
@Configuration
public class WebServiceConfig {

	@Bean
	public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(
			ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean<>(servlet, "/services/*");
	}

	@Bean(name = "sleepTracker")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema sleepSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("sleepTrackerPort");
		wsdl11Definition.setLocationUri("/services");
		wsdl11Definition.setTargetNamespace("http://sleep.soap.sose.it");
		wsdl11Definition.setSchema(sleepSchema);
		return wsdl11Definition;
	}

	@Bean
	public XsdSchema sleepSchema() {
		return new SimpleXsdSchema(new ClassPathResource("sleepTracker.xsd"));
	}
}