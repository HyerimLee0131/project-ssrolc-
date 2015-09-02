package com.ssrolc.exception;

import org.springframework.http.HttpStatus;

public class FileNotExistsException extends RuntimeException {
	
	
	public String getError() {
		return "File " + HttpStatus.NOT_FOUND.getReasonPhrase();
	}
	
	public Object[] getArgs() {
		return new Object[]{ };
	}
}
