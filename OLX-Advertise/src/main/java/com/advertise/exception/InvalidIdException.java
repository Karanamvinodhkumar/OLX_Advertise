package com.advertise.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidIdException extends RuntimeException {
	
	private String message;

	public InvalidIdException(String message) {
		super();
		this.message = message;
	}

	public InvalidIdException() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Invalid Id :" + message ;
	}
	
	

}
