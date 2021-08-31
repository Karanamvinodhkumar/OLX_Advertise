package com.advertise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

//import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;


@Service
public class UserServiceDelegateCircuitImpl implements UserServiceDelegate{
	
	
	@Autowired
	CircuitBreakerFactory circuitBeakerFactory;
	
	@Autowired
	RestTemplate restTemplate;
	
	
	@LoadBalanced
	public RestTemplate getRestTemplate()
	{
		return new RestTemplate();
	}

	/*
	 * @Override public boolean isValidUser(String authToken) { CircuitBreaker
	 * circuitBreaker = circuitBeakerFactory.create("AUTH_TOKEN_VALIDATION");
	 * HttpHeaders headers = new HttpHeaders(); headers.set("Authorization",
	 * authToken); HttpEntity entity = new HttpEntity(headers);
	 * ResponseEntity<Boolean> result =
	 * circuitBreaker.run(()->this.restTemplate.exchange(
	 * "http://localhost:7000/userdetails/token/validate", HttpMethod.GET, entity,
	 * Boolean.class),throwable-> fallbackForIsValidUser(authToken,throwable));
	 * 
	 * return result.getBody(); }
	 */

	@Override
	@CircuitBreaker(name = "AUTH_TOKEN_VALIDATION" , fallbackMethod = "fallbackForIsValidUser" )
	public boolean isValidUser(String authToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", authToken);
		HttpEntity entity = new HttpEntity(headers);
		ResponseEntity<Boolean> result = this.restTemplate.exchange("http://localhost:7000/userdetails/token/validate",
                HttpMethod.GET, entity, Boolean.class);
		
		return result.getBody();
	}

	public boolean fallbackForIsValidUser(String authToken, Throwable throwable) {
		System.out.println("Login service failed:"+throwable);
		return false;
	}

}
