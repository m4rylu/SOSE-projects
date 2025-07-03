package it.sose.soap.adapter.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.sose.soap.adapter.service.UmorSOAPAdapterService;


@RestController
@RequestMapping("/api/adapter/humor")
public class UmorSOAPAdapterController {
	private final UmorSOAPAdapterService adapterService;
	
	public UmorSOAPAdapterController(UmorSOAPAdapterService adapterService) {
		this.adapterService = adapterService;
	}
	
	@GetMapping("/print")
	public String print() {
		return adapterService.print();
	}
	
	@GetMapping("/add/{a}")
	public String add(@PathVariable int a) {
		return adapterService.add(a);
	}
	
	@GetMapping("/lastValues")
	public String lastValues() {
		return adapterService.lastValues();
	}
}
