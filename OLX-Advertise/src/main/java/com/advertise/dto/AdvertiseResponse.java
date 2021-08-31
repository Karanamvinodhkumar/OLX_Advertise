package com.advertise.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdvertiseResponse {
	
	@ApiModelProperty(value = "id of the advertise")
	private int id;
	@ApiModelProperty(value = "price of the advertise")
	private double price;
	@ApiModelProperty(value = "title of the advertise")
	private String title;
	@ApiModelProperty(value = "description of the advertise")
	private String description;
	@ApiModelProperty(value = "category of the advertise")
	private String category;
	@ApiModelProperty(value = "status of the advertise")
	private String status;
	@ApiModelProperty(value = "created date of the advertise")
	private java.util.Date createddate;
	@ApiModelProperty(value = "modified date of the advertise")
	private java.util.Date modifieddate;

}
