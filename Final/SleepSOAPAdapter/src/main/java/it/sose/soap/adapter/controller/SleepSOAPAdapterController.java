package it.sose.soap.adapter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.sose.soap.adapter.service.SleepSOAPAdapterService;


@RestController
@RequestMapping("/api/adapter/sleep")
public class SleepSOAPAdapterController {
	private final SleepSOAPAdapterService adapterService;
	
	public SleepSOAPAdapterController(SleepSOAPAdapterService adapterService) {
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
