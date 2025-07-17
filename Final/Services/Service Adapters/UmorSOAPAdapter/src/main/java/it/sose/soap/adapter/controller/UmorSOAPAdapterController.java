package it.sose.soap.adapter.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.sose.soap.adapter.service.UmorSOAPAdapterService;

/**
 * REST Controller that exposes a RESTful endpoint for retrieving mood-related advice
 * by acting as an adapter for the underlying SOAP-based UmorTracker service.
 * 
 * This class uses constructor-based dependency injection to obtain a reference
 * to the UmorTracker service implementation.
 */
@RestController
@RequestMapping("/api/adapter/humor")
public class UmorSOAPAdapterController {
	
    /**
     * Adapter service responsible for invoking the SOAP-based UmorTracker logic.
     * Declared as final because it's injected via constructor.
     */
	private final UmorSOAPAdapterService adapterService;
	
    /**
     * Constructor-based dependency injection for the adapter service.
     * Spring will inject the appropriate implementation automatically at runtime.
     * @param adapterService umorTracker SOAP service adapter
     */
	public UmorSOAPAdapterController(UmorSOAPAdapterService adapterService) {
		this.adapterService = adapterService;
	}
	
	/**
	 * Exposes a RESTful GET endpoint at /api/adapter/humor/print
	 * @return formatted string representing all stored sleep values
	 */
	@GetMapping("/print")
	public String print() {
		return adapterService.print();
	}
	
	/**
	 * Exposes a RESTful GET endpoint at /api/adapter/humor/lastValues
	 * @return confirmation message or an error message if input is invalid
     */
	@GetMapping("/add/{a}")
	public String add(@PathVariable int a) {
		return adapterService.add(a);
	}
	
	/**
	 * Exposes a RESTful GET endpoint at /api/adapter/humor/lastValues
	 * @return a string representation of the last 7 days' values
	 */
	@GetMapping("/lastValues")
	public String lastValues() {
		return adapterService.lastValues();
	}
}
