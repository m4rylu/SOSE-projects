package it.sose.soap.adapter.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.sose.advice.mood.ExecutionException_Exception;
import it.sose.advice.mood.InterruptedException_Exception;
import it.sose.soap.adapter.service.AdviceMoodSOAPAdapterService;


@RestController
@RequestMapping("/api/adapter/advice/mood")
public class AdviceMoodSOAPAdapterController {
	private final AdviceMoodSOAPAdapterService adapterService;
	
	public AdviceMoodSOAPAdapterController(AdviceMoodSOAPAdapterService adapterService) {
		this.adapterService = adapterService;
	}
	
	@GetMapping("/advice")
	public String adviceMood() throws ExecutionException_Exception, InterruptedException_Exception {
		return adapterService.adviceMood();
	}
}
