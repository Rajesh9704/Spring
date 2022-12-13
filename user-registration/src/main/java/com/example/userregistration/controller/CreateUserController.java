package com.example.userregistration.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.userregistration.model.User;
import com.example.userregistration.utils.UserUtils;

@RestController
public class CreateUserController {
	
	@PostMapping(value="/user/create", produces="application/json")
	private String createUser(@RequestBody User user) {
		
		String url = "http://restapi.adequateshop.com/api/Users";
		
		HttpEntity<User> entity = new HttpEntity<>(user, UserUtils.getHttpHeaders());
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = null;
		try {
			response = restTemplate.postForEntity(url, entity, String.class);
		} catch(HttpClientErrorException e) {
			return e.getResponseBodyAsString();
		}
		System.out.println("status code-->" + response.getStatusCode());
		System.out.println("response body-->" + response.getBody());
		return response.getBody();
	}
}
