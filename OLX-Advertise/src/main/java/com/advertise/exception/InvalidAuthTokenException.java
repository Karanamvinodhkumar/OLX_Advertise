package com.advertise.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class InvalidAuthTokenException extends RuntimeException {
	
	private String message;

	public InvalidAuthTokenException(String message) {
		super();
		this.message = message;
	}

	public InvalidAuthTokenException() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Invalid AuthToken:" + message;
	}
	
	
	
	

}
