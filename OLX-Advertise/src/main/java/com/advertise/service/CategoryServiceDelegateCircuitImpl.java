package com.advertise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.advertise.dto.CategoryDto;
import com.advertise.dto.StatusDto;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class CategoryServiceDelegateCircuitImpl implements CategoryServiceDelegate {

	@Autowired
	RestTemplate restTemplate;
	
	@LoadBalanced
	public RestTemplate getRestTemplate()
	{
		return new RestTemplate();
	}
	
	@Override
	@CircuitBreaker(name="CATEGORY",fallbackMethod = "fallbackForGetCategory")
	public CategoryDto getCategory(Integer id) {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity entity = new HttpEntity(headers);
		ResponseEntity<CategoryDto> result = restTemplate.exchange("http://localhost:8000/advertise/category/"+id,
                HttpMethod.GET, entity, new ParameterizedTypeReference<CategoryDto>() {});

        return result.getBody();
		
	}

	@Override
	@CircuitBreaker(name="STATUS",fallbackMethod = "fallbackForGetStatus")
	public StatusDto getStatus(Integer id) {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity entity = new HttpEntity(headers);
		ResponseEntity<StatusDto> result1 = restTemplate.exchange("http://localhost:8000/advertise/status/"+id,
		                                HttpMethod.GET, entity, new ParameterizedTypeReference<StatusDto>() {});
		
		
		
		return result1.getBody();
	}
	
	public CategoryDto fallbackForGetCategory(Integer id,Throwable throwable)
	{
		System.out.println("Category service failed:"+throwable);
		return null;
	}
	
	public StatusDto fallbackForGetStatus(Integer id,Throwable throwable)
	{
		System.out.println("Status service failed:"+throwable);
		return null;
	}

}
