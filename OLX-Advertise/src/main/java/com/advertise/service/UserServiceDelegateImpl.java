package com.advertise.service;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


public class UserServiceDelegateImpl implements UserServiceDelegate {

	@Autowired
	RestTemplate restTemplate;
	
	public RestTemplate getRestTemplate()
	{
		return new RestTemplate();
	}
	
	

	@Override
	public boolean isValidUser(String authToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", authToken);
		HttpEntity entity = new HttpEntity(headers);
		try {
		ResponseEntity<Boolean> result =this.restTemplate.exchange("http://localhost:7000/userdetails/token/validate",
		                                HttpMethod.GET, entity, Boolean.class);
		if(result.getStatusCode()==HttpStatus.OK)
		{
			return result.getBody();
		}
		else {
			return false;
		}
		}
		catch(Exception e)
		{
			return false;
		}
			
		
		
		
	}

}
