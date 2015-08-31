package com.ssrolc.exception;

import org.springframework.http.HttpStatus;


public class PopupNotAddException extends RuntimeException{

	public String getError() {
		return "Popup " + HttpStatus.NOT_ACCEPTABLE.getReasonPhrase();
	}
	
	public Object[] getArgs() {
		return new Object[]{ };
	}
}
