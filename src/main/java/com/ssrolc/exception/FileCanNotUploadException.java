package com.ssrolc.exception;

import org.springframework.http.HttpStatus;

public class FileCanNotUploadException extends RuntimeException {
	public HttpStatus getStatus() {
		return HttpStatus.NOT_ACCEPTABLE;
	}
	
	public String getError() {
		return HttpStatus.NOT_ACCEPTABLE.getReasonPhrase();
	}
	
	public String getCode() {
		return "FileCanNotUpload Type" + getClass().getSimpleName();
	}

	public Object[] getArgs() {
		return null;
	}
}
