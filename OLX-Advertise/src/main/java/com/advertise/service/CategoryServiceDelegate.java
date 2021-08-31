package com.advertise.service;

import com.advertise.dto.CategoryDto;
import com.advertise.dto.StatusDto;

public interface CategoryServiceDelegate {
	
	 public CategoryDto getCategory(Integer id);
	 public StatusDto getStatus(Integer id);
	

}
