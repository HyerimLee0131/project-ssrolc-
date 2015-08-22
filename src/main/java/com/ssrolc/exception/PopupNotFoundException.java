package com.ssrolc.exception;

import org.springframework.http.HttpStatus;


public class PopupNotFoundException extends ResourceNotFoundException{
	@Override
	public String getError() {
		return "Popup " + HttpStatus.NOT_FOUND.getReasonPhrase();
	}
	
	@Override
	public Object[] getArgs() {
		return new Object[]{ };
	}
}
