package com.ssrolc.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice("com.ssrolc.controller")
public class CommonExeptionHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(CommonExeptionHandler.class);
		
	
	@ExceptionHandler(value={ArticleNotAddException.class,BoardNotUploadException.class
			,FileCanNotUploadException.class,PopupNotAddException.class,RuntimeException.class})
	public String handlerRuntimeExeption(){
		return "common/error500";
	}
	
	@ExceptionHandler(value={BoardNotFoundException.class,ArticleNotFoundException.class
			,BoardCategoryNotFoundException.class,PopupNotFoundException.class
			,PrmediaNotFoundException.class,ResourceNotFoundException.class})
	public String handlerResourseNotFoundExeption(){
		return "common/error404";
	}
	
}