package com.advertise.service;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.advertise.dto.Advertise;
import com.advertise.dto.AdvertiseRequest;
import com.advertise.dto.AdvertiseResponse;
import com.advertise.entity.AdvertiseEntity;

import io.swagger.annotations.ApiParam;

@Service
public interface AdvertiseService {
	
	public ResponseEntity<?> createNewAdvertise(AdvertiseRequest advertise,String authToken);
	
	public ResponseEntity<?> updateAdvertise(int id,String authToken,AdvertiseRequest advertise);
	
	public Boolean deleteAdvertiseById(int id,String authToken);
	
	public List<AdvertiseResponse> getAllAdvertise(String authToken);
	
	public AdvertiseResponse getAdvertiseById(String authToken,int id);
	
	public AdvertiseResponse getAdvertisedetailsById(String authToken,int id);
	
	//public List<Advertise> searchStatus(String searchText);
	
	//public Advertise search(String searchText);
	
	
	
	
	//ResponseEntity<?> getStatus(String searchText);
	ResponseEntity<?> filterandsearch(int page, int size, String title, Integer category, Integer status, Double price,String dateCondition, String onDate,String fromDate, String toDate,String sortBy, String order);
	
	ResponseEntity<?> getText(String searchText);
	
	
	
	

}
