package it.sose.soap.sleep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point of the Spring Boot application.
 * This class bootstraps the entire application by invoking Spring Boot's auto-configuration,
 * component scanning, and application context creation.
 */
@SpringBootApplication
public class SleepTrackerSoapSpringServiceApplication {

    /**
     * Main method used to launch the Spring Boot application.
     * @param args optional command-line arguments
     */
	public static void main(String[] args) {
		// Start the Spring Boot application
		SpringApplication.run(SleepTrackerSoapSpringServiceApplication.class, args);
	}

}
