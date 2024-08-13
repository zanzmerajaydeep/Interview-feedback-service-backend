package com.ifs.exception;

import org.springframework.http.HttpStatus;

public class DuplicateEmailException extends RuntimeException{
	public DuplicateEmailException(String msg) {
		super(msg + " |  status code : "+HttpStatus.BAD_REQUEST);
	}

}
