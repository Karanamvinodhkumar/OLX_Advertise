package com.advertise.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AdvertiseNotFoundException extends RuntimeException {
	private String message;

	public AdvertiseNotFoundException(String message) {
		super();
		this.message = message;
	}

	public AdvertiseNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "No records found with given text:" + message;
	}

}
