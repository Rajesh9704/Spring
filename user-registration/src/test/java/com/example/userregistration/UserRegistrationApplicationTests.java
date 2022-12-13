package com.example.userregistration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;

import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


@SpringBootTest
public class UserRegistrationApplicationTests {
	
	static JSONObject userObject;
	static HttpHeaders headers;
	static RestTemplate restTemplate;
	
	@Test
	public void registrationTest() throws AssertionError {
		System.out.println("inside registrationTest");
		String url = "http://restapi.adequateshop.com/api/AuthAccount/Registration";
		
		HttpEntity<String> request = new HttpEntity<>(userObject.toString(), headers);
		
		ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
		
		System.out.println("status code-->" + response.getStatusCode());
		System.out.println("response body-->" + response.getBody());
		
		assertEquals(200, response.getStatusCodeValue());

	}
	
	@Test
	public void loginTest() throws AssertionError {
			
		String url = "http://restapi.adequateshop.com/api/AuthAccount/Login";
		
		HttpEntity<String> request = new HttpEntity<>(userObject.toString(), headers);
		
		ResponseEntity<String> response = restTemplate.postForEntity(url, request , String.class);
		
		System.out.println("status code-->" + response.getStatusCode());
		System.out.println("response body-->" + response.getBody());
		
		assertEquals(200, response.getStatusCodeValue());
		
	}
		
	@Test 
	public void createUserTest() throws JSONException,AssertionError {
		System.out.println("inside createUserTest");
		String url = "http://restapi.adequateshop.com/api/Users";
		
		headers.setBearerAuth("269bde5b-ed72-4f8e-8c93-308bc3d316f6");
		Random random = new Random();
		userObject.put("name", "name"+random.nextInt());
		userObject.put("email", "name"+random.nextInt()+"@mail.com");
		
		HttpEntity<String> request = new HttpEntity<>(userObject.toString(), headers);
		
		ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
		
		System.out.println("status code-->" + response.getStatusCode());
		System.out.println("response body-->" + response.getBody());

		assertEquals(201, response.getStatusCodeValue());
	}
	
	@Test
	public void getUserByIdTest() throws AssertionError {
		System.out.println("inside getUserByIdTest");
		int userid = 195929;
		String url = "http://restapi.adequateshop.com/api/Users/" + userid;
		
		headers.setBearerAuth("269bde5b-ed72-4f8e-8c93-308bc3d316f6");
		
		HttpEntity<String> entity = new HttpEntity<>(headers);
		
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		
		System.out.println("status code-->" + response.getStatusCode());
		System.out.println("response body-->" + response.getBody());
		
		assertEquals(200, response.getStatusCodeValue());

	}
	
	@Test
	public void deleteUserByIdTest() throws AssertionError {
		System.out.println("inside deleteUserByIdTest");
		int userid = 195929;
		String url = "http://restapi.adequateshop.com/api/Users/" + userid;
		
		headers.setBearerAuth("269bde5b-ed72-4f8e-8c93-308bc3d316f6");
		
		HttpEntity<String> entity = new HttpEntity<>(headers);
		
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class);
		
		System.out.println("status code-->" + response.getStatusCode());
		System.out.println("response body-->" + response.getBody());
		assertEquals(200, response.getStatusCodeValue());
	}
	
	@Test
	public void updateUserByIdTest() throws JSONException, AssertionError {
		
		System.out.println("inside updateUserByIdTest");
		int userid = 195929;
		String url = "http://restapi.adequateshop.com/api/Users/" + userid;
		
		headers.setBearerAuth("269bde5b-ed72-4f8e-8c93-308bc3d316f6");
		
		Random random = new Random();
		userObject.put("name", "name"+random.nextInt());
		userObject.put("email", "name"+random.nextInt()+"@mail.com");
		
		
		HttpEntity<String> entity = new HttpEntity<>(userObject.toString(), headers);
		
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
		
		System.out.println("status code-->" + response.getStatusCode());
		System.out.println("response body-->" + response.getBody());
		assertEquals(201, response.getStatusCodeValue());
		
	}
		
	@BeforeClass
	public static void runBeforeAllMethods() throws JSONException{
		restTemplate = new RestTemplate();
		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		userObject = new JSONObject();
		
		userObject.put("id", 4545);
		userObject.put("name", "raj");
		userObject.put("email", "raj2323@mail.com");
		userObject.put("location", "Ind");
		userObject.put("password", "password");
		userObject.put("createddat", "");
	}
	

}
