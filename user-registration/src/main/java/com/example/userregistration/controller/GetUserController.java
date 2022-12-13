package com.example.userregistration.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.userregistration.exception.UserNotFoundException;
import com.example.userregistration.utils.UserUtils;


@RestController
public class GetUserController {
	
	RestTemplate restTemplate = new RestTemplate();

	@GetMapping(value="/user/get/{userid}" , produces="application/json")
	private ResponseEntity<Object> getUserById(@PathVariable int userid) {
		
		String url = "http://restapi.adequateshop.com/api/Users/" + userid;
		
		HttpEntity<String> entity = new HttpEntity<>(UserUtils.getHttpHeaders());
		
		try {
			return restTemplate.exchange(url, HttpMethod.GET, entity, Object.class);
		} catch (Exception e) {
			throw new UserNotFoundException();
		}
	}
	
	@RequestMapping(value = "/user/get/page/{pageid}", produces = "application/json")
	private String getUserByPage(@PathVariable int pageid) {

		String url = "http://restapi.adequateshop.com/api/Users?page=" + pageid;
		HttpEntity<String> entity = new HttpEntity<>(UserUtils.getHttpHeaders());

		ResponseEntity<String> response = null;
		try {
			response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		} catch (HttpClientErrorException e) {
			return e.getResponseBodyAsString();
		}
		System.out.println("status code-->" + response.getStatusCode());
		System.out.println("response body-->" + response.getBody());
		return response.getBody();
	}
}
