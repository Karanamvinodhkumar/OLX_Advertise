package com.advertise.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Optional;
import java.util.*;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java. util. Collection;

import com.advertise.dto.Advertise;
import com.advertise.dto.AdvertiseList;
import com.advertise.dto.AdvertiseRequest;
import com.advertise.dto.AdvertiseResponse;
import com.advertise.dto.CategoryDto;
import com.advertise.dto.StatusDto;
import com.advertise.entity.AdvertiseEntity;
import com.advertise.exception.AdvertiseNotFoundException;
import com.advertise.exception.InvalidAuthTokenException;
import com.advertise.exception.InvalidIdException;
import com.advertise.repository.AdvertiseRepo;
import com.advertise.repository.CustomAdvertiseRepo;
import com.advertise.dto.AdvertiseRequest;

import lombok.val;


@Service
public class AdvertiseServiceImpl implements AdvertiseService {

	@Autowired
	private AdvertiseRepo advertiseRepo;
	
	@Autowired
	UserServiceDelegate userServiceDelegate;
	
	
	@Autowired
	CategoryServiceDelegate categoryService;
	


	@Override
	public ResponseEntity<?> createNewAdvertise(AdvertiseRequest advertise, String authToken) {
		if (!userServiceDelegate.isValidUser(authToken)) {
			throw new InvalidAuthTokenException("" + authToken);
		}
		
		CategoryDto category = categoryService.getCategory(advertise.getCategoryId());
		StatusDto status = categoryService.getStatus(advertise.getStatusId());
		AdvertiseEntity ade = new AdvertiseEntity(advertise.getPrice(), advertise.getTitle(),
				advertise.getDescription(), advertise.getCategoryId(), advertise.getStatusId());
		ade = advertiseRepo.save(ade);
		AdvertiseResponse ad = new AdvertiseResponse(ade.getId(), ade.getPrice(), ade.getTitle(), ade.getDescription(),
				category.getCategory(), status.getStatus(), ade.getCreateddate(), ade.getModifieddate());
		return ResponseEntity.ok(ad);
	}

	@Override
	public ResponseEntity<?> updateAdvertise(int id, String authToken, AdvertiseRequest advertise) {
		if (!userServiceDelegate.isValidUser(authToken)) {
			throw new InvalidAuthTokenException("" + authToken);
			
		}
		
			Optional<AdvertiseEntity> ads = advertiseRepo.findById(id);
			CategoryDto category = categoryService.getCategory(advertise.getCategoryId());
			StatusDto status = categoryService.getStatus(advertise.getStatusId());
			if (ads.isPresent()) {
				AdvertiseEntity advertiseentity = ads.get();
				advertiseentity.setPrice(advertise.getPrice());
				advertiseentity.setCategory(advertise.getCategoryId());
				advertiseentity.setDescription(advertise.getDescription());
				advertiseentity.setTitle(advertise.getTitle());
				advertiseentity.setStatus(advertise.getStatusId());
				advertiseentity = advertiseRepo.save(advertiseentity);
				AdvertiseResponse ad = new AdvertiseResponse(advertiseentity.getId(), advertiseentity.getPrice(), advertiseentity.getTitle(), advertiseentity.getDescription(),
						category.getCategory(), status.getStatus(), advertiseentity.getCreateddate(), advertiseentity.getModifieddate());
				return ResponseEntity.ok(ad);

			}
			
			throw new InvalidIdException("" + id);

		}
		

	

	@Override
	public Boolean deleteAdvertiseById(int id, String authToken) {
		if (!userServiceDelegate.isValidUser(authToken)) {
			throw new InvalidAuthTokenException("" + authToken);
			
		}
			Optional<AdvertiseEntity> ads = advertiseRepo.findById(id);
			if (ads.isPresent()) {
				advertiseRepo.deleteById(id);
				return true;
			}
			throw new InvalidIdException("" + id);
		}
		
	

	@Override
	public List<AdvertiseResponse> getAllAdvertise(String authToken) {
		if (userServiceDelegate.isValidUser(authToken)) {
			
			List<AdvertiseEntity> list = advertiseRepo.findAll();
			List<AdvertiseResponse> ll = new ArrayList<>();
			for (AdvertiseEntity advdata : list) {
				CategoryDto category = categoryService.getCategory(advdata.getCategory());
				StatusDto status = categoryService.getStatus(advdata.getStatus());
				AdvertiseResponse adv = new AdvertiseResponse(advdata.getId(), advdata.getPrice(), advdata.getTitle(),
						advdata.getDescription(),  category.getCategory(),status.getStatus(), advdata.getModifieddate(),
						advdata.getCreateddate());
				ll.add(adv);
			}
			return ll;
		}
		throw new InvalidAuthTokenException("" + authToken);

	}

	@Override
	public AdvertiseResponse getAdvertiseById(String authToken, int id) {
		if (userServiceDelegate.isValidUser(authToken)) {
			Optional<AdvertiseEntity> ads = advertiseRepo.findById(id);
			if (ads.isPresent()) {
				AdvertiseEntity advdata = ads.get();
				CategoryDto category = categoryService.getCategory(advdata.getCategory());
				StatusDto status = categoryService.getStatus(advdata.getStatus());
				AdvertiseResponse ad = new AdvertiseResponse(advdata.getId(), advdata.getPrice(), advdata.getTitle(),
						advdata.getDescription(),  category.getCategory(),status.getStatus(), advdata.getModifieddate(),
						advdata.getCreateddate());
				return ad;
			}
			throw new InvalidIdException("" + id);
		}
		throw new InvalidAuthTokenException("" + authToken);
	}

	@Override
	public AdvertiseResponse getAdvertisedetailsById(String authToken, int id) {
		if (userServiceDelegate.isValidUser(authToken)) {
			Optional<AdvertiseEntity> ads = advertiseRepo.findById(id);
			if (ads.isPresent()) {
				AdvertiseEntity advdata = ads.get();
				CategoryDto category = categoryService.getCategory(advdata.getCategory());
				StatusDto status = categoryService.getStatus(advdata.getStatus());
				AdvertiseResponse ad = new AdvertiseResponse(advdata.getId(), advdata.getPrice(), advdata.getTitle(),
						advdata.getDescription(),  category.getCategory(),status.getStatus(), advdata.getModifieddate(),
						advdata.getCreateddate());
				return ad;
			}
			throw new InvalidIdException("" + id);
		}
		throw new InvalidAuthTokenException("" + authToken);

	}


	@Override
	public ResponseEntity<?> getText(String searchText) {
		
		List<AdvertiseEntity> ads = advertiseRepo.findByText(searchText);
		List<AdvertiseResponse> ll = new ArrayList<>();
		for(AdvertiseEntity advdata:ads)
		{
			
			CategoryDto category = categoryService.getCategory(advdata.getCategory());
			StatusDto status = categoryService.getStatus(advdata.getStatus());
			AdvertiseResponse ad = new AdvertiseResponse(advdata.getId(), advdata.getPrice(), advdata.getTitle(),
					advdata.getDescription(),  category.getCategory(),status.getStatus(), advdata.getModifieddate(),
					advdata.getCreateddate());
			ll.add(ad);
		}
		
		
		if(ll.size()==0)
		{
			throw new AdvertiseNotFoundException(""+searchText);
		}
		return ResponseEntity.ok(ll);

	}

	


	private void set(List<AdvertiseEntity> advertiseList, List<AdvertiseResponse> advertiseResponseList) {

		for (AdvertiseEntity advdata : advertiseList)
		{
			CategoryDto category = categoryService.getCategory(advdata.getCategory());
			StatusDto status = categoryService.getStatus(advdata.getStatus());
			AdvertiseResponse ad = new AdvertiseResponse(advdata.getId(), advdata.getPrice(), advdata.getTitle(),
					advdata.getDescription(),  category.getCategory(),status.getStatus(), advdata.getModifieddate(),
					advdata.getCreateddate());
			advertiseResponseList.add(ad);
		}
		
	}

	private List<AdvertiseEntity> sort(List<AdvertiseEntity> advertiseList, String sortBy) {
		switch (sortBy){
		case "category" :
			advertiseList = advertiseList.stream().sorted(Comparator.comparingInt(AdvertiseEntity::getCategory)).collect(Collectors.toList());
			break;
		case "title" :
			advertiseList = advertiseList.stream().sorted(Comparator.comparing(AdvertiseEntity::getTitle)).collect(Collectors.toList());
			break;
		case "status" :
			advertiseList = advertiseList.stream().sorted(Comparator.comparingInt(AdvertiseEntity::getStatus)).collect(Collectors.toList());
			break;
		case "price" :
			advertiseList = advertiseList.stream().sorted(Comparator.comparingDouble(AdvertiseEntity::getPrice)).collect(Collectors.toList());
			break;
		case "createddate" :
			advertiseList = advertiseList.stream().sorted(Comparator.comparing(AdvertiseEntity::getCreateddate)).collect(Collectors.toList());
			break;
		default:
	}
	return advertiseList;
}
	

	
	private Date getDate(String createdDate) throws ParseException {
		if (createdDate == null)
		{
			return new Date();
		}
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.parse(createdDate);
	}

	@Override
	public ResponseEntity<?> filterandsearch(int page, int size, String title, Integer category, Integer status, Double price,
			String dateCondition, String onDate, String fromDate, String toDate, String sortBy, String order) {
		
		List<AdvertiseEntity> advertiseList = advertiseRepo.filterandsearch(page,size,title,category,status,price,dateCondition,onDate,fromDate,toDate);

		if (sortBy !=null)
			advertiseList = sort(advertiseList,sortBy);
		if (order !=null && order.equals("des"))
			Collections.reverse(advertiseList);
		
		List<AdvertiseResponse> advertiseResponseList = new ArrayList<>();
		set(advertiseList, advertiseResponseList);
		return ResponseEntity.ok(new AdvertiseList(advertiseResponseList));
		//return ResponseEntity.ok(new AdvertiseList(advertiseResponseList));

	}	


}
