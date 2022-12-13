package com.example.userregistration.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.userregistration.model.User;
import com.example.userregistration.utils.UserUtils;

@RestController
public class UpdateUserController {
	
	@RequestMapping(value = "/user/update/{userid}", method = RequestMethod.POST, produces = "application/json")
	private String updateUserById(@RequestBody User user, @PathVariable int userid) {

		String url = "http://restapi.adequateshop.com/api/Users/" + userid;

		HttpEntity<User> entity = new HttpEntity<>(user, UserUtils.getHttpHeaders());

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = null;
		try {
			response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
		} catch (HttpClientErrorException e) {
			return e.getResponseBodyAsString();
		}
		System.out.println("status code-->" + response.getStatusCode());
		System.out.println("response body-->" + response.getBody());
		return response.getBody();
	}
}
