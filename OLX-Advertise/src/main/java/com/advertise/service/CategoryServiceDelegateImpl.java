package com.advertise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.advertise.dto.CategoryDto;
import com.advertise.dto.StatusDto;
import com.advertise.exception.InvalidIdException;


public class CategoryServiceDelegateImpl implements CategoryServiceDelegate {
	
	
	@Autowired
	RestTemplate restTemplate;
	
	public RestTemplate getRestTemplate()
	{
		return new RestTemplate();
	}

	@Override
	public CategoryDto getCategory(Integer id) {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity entity = new HttpEntity(headers);
		try {
		ResponseEntity<CategoryDto> result = restTemplate.exchange("http://localhost:8000/advertise/category/"+id,
		                                HttpMethod.GET, entity, new ParameterizedTypeReference<CategoryDto>() {});
		
		
		
		return result.getBody();
	
		
		}
		catch(Exception e)
		{
			throw new InvalidIdException(""+id);
		}
	}
	
	public StatusDto getStatus(Integer id)
	{
		HttpHeaders headers = new HttpHeaders();
		HttpEntity entity = new HttpEntity(headers);
		try {
		ResponseEntity<StatusDto> result1 = restTemplate.exchange("http://localhost:8000/advertise/status/"+id,
		                                HttpMethod.GET, entity, new ParameterizedTypeReference<StatusDto>() {});
		
		
		
		return result1.getBody();
	
		
		}
		catch(Exception e)
		{
			throw new InvalidIdException(""+id);
		}
	}
	

}
