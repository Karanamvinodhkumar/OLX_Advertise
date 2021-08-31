package com.advertise.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.advertise.dto.Advertise;
import com.advertise.dto.AdvertiseRequest;
import com.advertise.dto.AdvertiseResponse;
import com.advertise.entity.AdvertiseEntity;
import com.advertise.service.AdvertiseService;
import com.advertise.service.UserServiceDelegate;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/adv")
public class AdvertiseController {
	@Autowired
	private AdvertiseService advertiseService;
	
	
	@Autowired
	UserServiceDelegate userservice;

	@ApiOperation(value = "Add an advertise", notes = "To add a new advertise")
	@PostMapping(value = "/advertise", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createNewAdvertise(@RequestBody AdvertiseRequest advertise,
			@RequestHeader("Authorization") String authToken) {

		return advertiseService.createNewAdvertise(advertise, authToken);

	}

	@ApiOperation(value = "Update an advertise", notes = "To update an existing advertise")
	@PutMapping(value = "/advertise/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateAdvertise(@ApiParam(value = "id of advertise") @PathVariable int id,
			@RequestHeader("Authorization") String authToken, @RequestBody AdvertiseRequest advertise) {

		return advertiseService.updateAdvertise(id, authToken, advertise);

	}

	@ApiOperation(value = "Delete an advertise", notes = "To delete an existing advertise")
	@DeleteMapping(value = "/user/advertise/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> deleteAdvertiseById(@ApiParam(value = "id of advertise") @PathVariable int id,
			@RequestHeader("Authorization") String authToken) {

		return new ResponseEntity<Boolean>(advertiseService.deleteAdvertiseById(id, authToken), HttpStatus.OK);
	}

	@ApiOperation(value = "Get all advertises", notes = "To get an all advertises")
	@GetMapping(value = "/advertise", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AdvertiseResponse>> getAllAdvertise(@RequestHeader("Authorization") String authToken) {

		return new ResponseEntity<>(advertiseService.getAllAdvertise(authToken), HttpStatus.OK);

	}

	@ApiOperation(value = "Get an advertise by id", notes = "To get an existing advertise by id")
	@GetMapping(value = "/user/advertise/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AdvertiseResponse> getAdvertiseById(@RequestHeader("Authorization") String authToken,
			@ApiParam(value = "id of advertise") @PathVariable("id") int id) {

		return new ResponseEntity<>(advertiseService.getAdvertiseById(authToken, id), HttpStatus.OK);

	}

	@ApiOperation(value = "Get an advertise by id", notes = "To get an existing advertise by id")
	@GetMapping(value = "/advertise/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AdvertiseResponse> getAdvertisedetailsById(@RequestHeader("Authorization") String authToken,
			@ApiParam(value = "id of advertise") @PathVariable("id") int id) {

		return new ResponseEntity<>(advertiseService.getAdvertisedetailsById(authToken, id), HttpStatus.OK);

	}

	@GetMapping(value = "/search/filter" ,produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "Search By Criteria", notes =  "This Service Returns List of Ads based on Particular Search Criteria from OLX App")
	@ResponseBody
	public ResponseEntity<?> filterandsearch(@RequestParam(required = false) String title,
			@RequestParam(required = false) Integer category,
			@RequestParam(required = false) Integer status,
			@RequestParam(required = false) Double price,
			@RequestParam(required = false) String dateCondition,
			@RequestParam(required = false) String onDate,
			@RequestParam(required = false) String fromDate,
			@RequestParam(required = false) String toDate,
			@RequestParam(required = false) String sortBy,
			@RequestParam(required = false) String order,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "3") int size) {
   return advertiseService.filterandsearch(page,size,title,category,status,price,dateCondition,onDate,fromDate,toDate,sortBy,order);
}
	
	  @ApiOperation(value = "Get an advertise by searchtext", notes =
	  "To Match advertisements using the peovided searchText within all fields of an advertise.")
	   @GetMapping(value = "/search/{searchText}")
	  @ResponseBody
	  public ResponseEntity<?> getText(@PathVariable String searchText)
	  { 
		  
		 return advertiseService.getText(searchText);
		
	  
	  }
	 

	
}
