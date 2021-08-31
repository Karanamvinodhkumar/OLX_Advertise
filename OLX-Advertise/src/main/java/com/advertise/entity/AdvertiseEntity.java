package com.advertise.entity;


import java.util.Date;

//import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@Data
@Table(name = "advertise")

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString


public class AdvertiseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private Double price;
	
	private String title;
	
	private String description;
	
	private Integer category;
	
	private Integer status;
	
	@Column(nullable = false,updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createddate = new Date();
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date modifieddate = new Date();

	public AdvertiseEntity(double price, String title, String description, Integer category, Integer status) {
		super();
		this.price = price;
		this.title = title;
		this.description = description;
		this.category = category;
		this.status = status;
		
	}
	
	
	

}
