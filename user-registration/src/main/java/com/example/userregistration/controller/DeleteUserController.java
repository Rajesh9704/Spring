package com.example.userregistration.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.userregistration.exception.UserNotFoundException;
import com.example.userregistration.utils.UserUtils;

@RestController
public class DeleteUserController {
	
	@RequestMapping(value="/user/delete/{userid}" , method=RequestMethod.DELETE, produces="application/json")
	private ResponseEntity<String> deleteUserById(@PathVariable int userid) {
		
		String url = "http://restapi.adequateshop.com/api/Users/" + userid;
		
		HttpEntity<String> entity = new HttpEntity<>(UserUtils.getHttpHeaders());
		
		RestTemplate restTemplate = new RestTemplate();
		try {

			return restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class);
		
		} catch (Exception e) {
			throw new UserNotFoundException();
		}
	}
	
}
