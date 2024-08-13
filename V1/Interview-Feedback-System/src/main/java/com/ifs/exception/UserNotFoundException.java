package com.ifs.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends RuntimeException {
	
	public UserNotFoundException(String msg) {
		super(msg + " |  status code : "+HttpStatus.NOT_FOUND);
	}

}
