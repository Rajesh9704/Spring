package com.example.userregistration.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.userregistration.model.User;

@RestController
public class RegistrationAndLoginController {

	RestTemplate restTemplate = new RestTemplate();

	@PostMapping(value="/registration" , produces="application/json")
	private String authRegistration(@RequestBody User user) {
		
		String url = "http://restapi.adequateshop.com/api/AuthAccount/Registration";
		
		ResponseEntity<String> response = null;
		try {
			response = restTemplate.postForEntity(url, user, String.class);
		} catch(HttpClientErrorException e) {
			return e.getResponseBodyAsString();
		}
		System.out.println("status code-->" + response.getStatusCode());
		System.out.println("response body-->" + response.getBody());
		return response.getBody();
	}
	
	@PostMapping(value="/login" , produces="application/json")
	private String login(@RequestBody User user) {
		
		String url = "http://restapi.adequateshop.com/api/AuthAccount/Login";
		
		ResponseEntity<String> response = null;
		try {
			response = restTemplate.postForEntity(url, user, String.class);
		} catch(HttpClientErrorException e) {
			return e.getResponseBodyAsString();
		}
		
		System.out.println("status code-->" + response.getStatusCode());
		System.out.println("response body-->" + response.getBody());
		return response.getBody();
	}
	
	
	
}
