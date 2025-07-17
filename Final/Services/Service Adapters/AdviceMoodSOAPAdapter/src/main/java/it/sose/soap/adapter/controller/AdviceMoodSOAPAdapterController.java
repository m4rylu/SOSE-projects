package it.sose.soap.adapter.controller;


import java.util.concurrent.ExecutionException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.sose.advice.mood.ExecutionException_Exception;
import it.sose.advice.mood.InterruptedException_Exception;
import it.sose.soap.adapter.service.AdviceMoodSOAPAdapterService;

/**
 * REST Controller that exposes a RESTful endpoint for retrieving mood-related advice
 * by acting as an adapter for the underlying SOAP-based AdviceMood service.
 * 
 * This class uses constructor-based dependency injection to obtain a reference
 * to the AdviceMood service implementation.
 */
@RestController
@RequestMapping("/api/adapter/advice/mood")
public class AdviceMoodSOAPAdapterController {
	
    /**
     * Adapter service responsible for invoking the SOAP-based AdviceMood logic.
     * Declared as final because it's injected via constructor.
     */
	private final AdviceMoodSOAPAdapterService adapterService;
	
    /**
     * Constructor-based dependency injection for the adapter service.
     * Spring will inject the appropriate implementation automatically at runtime.
     * @param adapterService Advice Mood SOAP service adapter
     */
	public AdviceMoodSOAPAdapterController(AdviceMoodSOAPAdapterService adapterService) {
		this.adapterService = adapterService;
	}
	/**
	 * Exposes a RESTful GET endpoint at /api/adapter/advice/mood/advice
	 * @return a string containing personalized mood advice.
	 * @throws ExecutionException_Exception if an error occurs during SOAP execution.
	 * @throws InterruptedException_Exception if the async SOAP call is interrupted.
	 */
	@GetMapping("/advice")
	public String adviceMood() throws ExecutionException_Exception, InterruptedException_Exception {
		return adapterService.adviceMood();
	}
}
