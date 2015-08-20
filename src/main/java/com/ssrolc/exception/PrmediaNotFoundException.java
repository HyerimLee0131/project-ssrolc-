package com.ssrolc.exception;

import org.springframework.http.HttpStatus;


public class PrmediaNotFoundException extends ResourceNotFoundException{
	@Override
	public String getError() {
		return "Prmedia " + HttpStatus.NOT_FOUND.getReasonPhrase();
	}
	
	@Override
	public Object[] getArgs() {
		return new Object[]{ };
	}
}
