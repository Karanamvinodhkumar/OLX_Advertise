package com.advertise.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel(value = "List of Advertisements")
@AllArgsConstructor
@NoArgsConstructor
public class AdvertiseList {
	
	private List<AdvertiseResponse> advertises;

}
