package it.sose.rest.advice.coffee.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.sose.rest.advice.coffee.prosumer.AdviceCoffee;

@RestController
@RequestMapping("/advice")
public class AdviceCoffeeController {
	private final AdviceCoffee adviceCoffee;
	
    public AdviceCoffeeController(AdviceCoffee adviceCoffee) {
        this.adviceCoffee = adviceCoffee;
    }
	
	@GetMapping("/advice")
	public String getAdvice() {
		return adviceCoffee.getAdviceCoffee();
	}

}
