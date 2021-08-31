package com.advertise.repository;

import java.util.List;

import org.springframework.stereotype.Service;

import com.advertise.entity.AdvertiseEntity;


public interface CustomAdvertiseRepo {
	List<AdvertiseEntity> filterandsearch(int page, int size, String title, Integer category, Integer status,
			Double price, String dateCondition, String onDate, String fromDate, String toDate);

}
