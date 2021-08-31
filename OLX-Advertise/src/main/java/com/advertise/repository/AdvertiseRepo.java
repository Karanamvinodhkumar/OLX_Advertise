package com.advertise.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.advertise.dto.Advertise;
import com.advertise.entity.AdvertiseEntity;
@Repository
public interface AdvertiseRepo extends JpaRepository<AdvertiseEntity, Integer>,CustomAdvertiseRepo,JpaSpecificationExecutor<AdvertiseEntity> {
	
	
	/*
	 * @Query("SELECT a FROM AdvertiseEntity a WHERE a.status =:searchText")
	 * List<AdvertiseEntity> findByStatus(String searchText);
	 */
	  
	  
		
     @Query("SELECT a FROM AdvertiseEntity a WHERE a.title LIKE %:searchText% or a.category LIKE %:searchText% or a.description LIKE %:searchText% ")
	 List <AdvertiseEntity> findByText(String searchText);
     
	
	 Page<AdvertiseEntity> findAll(Pageable pageable);



	
}
