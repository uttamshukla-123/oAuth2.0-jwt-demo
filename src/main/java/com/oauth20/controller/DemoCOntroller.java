package com.oauth20.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoCOntroller {
	
	@RequestMapping("/home")
	public String index() {
		return "Greetings from Spring Boot!";
	}


}
