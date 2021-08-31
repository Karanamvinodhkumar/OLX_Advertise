package com.advertise.dto;

import java.sql.Date;
import java.util.List;

import com.advertise.entity.AdvertiseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@Setter
@Getter
@ToString
@ApiModel(value="Advertise Details")
public class Advertise {
	
	
	
	@ApiModelProperty(value = "id of the advertise")
	private int id;
	@ApiModelProperty(value = "price of the advertise")
	private double price;
	@ApiModelProperty(value = "title of the advertise")
	private String title;
	@ApiModelProperty(value = "description of the advertise")
	private String description;
	@ApiModelProperty(value = "category of the advertise")
	private Integer categoryId;
	@ApiModelProperty(value = "status of the advertise")
	private Integer statusId;
	@ApiModelProperty(value = "created date of the advertise")
	private java.util.Date createddate;
	@ApiModelProperty(value = "modified date of the advertise")
	private java.util.Date modifieddate;
	public Advertise(int id, double price, String title, String description, Integer categoryId, Integer statusId,
			java.util.Date createddate, java.util.Date modifieddate) {
		super();
		this.id = id;
		this.price = price;
		this.title = title;
		this.description = description;
		this.categoryId = categoryId;
		this.statusId = statusId;
		this.createddate = createddate;
		this.modifieddate = modifieddate;
	}
	
	
	
	
	
	
	
	
	

}
