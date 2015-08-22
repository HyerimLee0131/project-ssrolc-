package com.ssrolc.exception;

import org.springframework.http.HttpStatus;


public class PopupNotAddException extends ResourceNotFoundException{
	@Override
	public String getError() {
		return "Popup " + HttpStatus.NOT_ACCEPTABLE.getReasonPhrase();
	}
	
	@Override
	public Object[] getArgs() {
		return new Object[]{ };
	}
}
