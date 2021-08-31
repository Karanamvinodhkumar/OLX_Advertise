package com.advertise.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Information of the Advertisement to be Added/Updated")
public class AdvertiseRequest {
	@ApiModelProperty(value = "ID of Advertisement")
	private Integer id;
	
	@ApiModelProperty(value = "Title of Advertisement")
	private String title;
	
	@ApiModelProperty(value = "Price of Product")
	private double price;
	
	@ApiModelProperty(value = "ID of Category under which the Product is Listed")
	private Integer categoryId;
	
	@ApiModelProperty(value = "Description of Advertisement")
	private String description;

	@ApiModelProperty(value = "ID of Advertisement Status")
	private Integer statusId;

}
