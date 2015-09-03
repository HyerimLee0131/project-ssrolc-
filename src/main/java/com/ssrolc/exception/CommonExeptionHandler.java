package com.ssrolc.exception;

import java.io.FileNotFoundException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice("com.ssrolc.controller")
public class CommonExeptionHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(CommonExeptionHandler.class);
		
	
	@ExceptionHandler(value={ArticleNotAddException.class,BoardNotUploadException.class
			,FileCanNotUploadException.class,PopupNotAddException.class,RuntimeException.class})
	public String handlerRuntimeExeption(HttpServletResponse res){
		res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		return "common/error500";
	}
	
	@ExceptionHandler(value={BoardNotFoundException.class,ArticleNotFoundException.class
			,BoardCategoryNotFoundException.class,PopupNotFoundException.class
			,PrmediaNotFoundException.class,ResourceNotFoundException.class})
	public String handlerResourseNotFoundExeption(HttpServletResponse res){
		res.setStatus(HttpServletResponse.SC_NOT_FOUND);
		return "common/error404";
	}
	
	@ExceptionHandler(value={FileNotFoundException.class})
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	public void handlerFileNotFoundException(){
		
	}
	
}
