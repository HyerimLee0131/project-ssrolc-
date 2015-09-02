package com.ssrolc.exception;

import org.springframework.http.HttpStatus;

public class ArticleNotAddException extends RuntimeException{
private final String boardTable;
	
	public ArticleNotAddException(String boardTable) {
		this.boardTable = boardTable;
	}

	public String getBoardTable() {
		return boardTable;
	}
	
	public String getError() {
		return "Article " + HttpStatus.NOT_ACCEPTABLE.getReasonPhrase();
	}
	
	public Object[] getArgs() {
		return new Object[]{ boardTable};
	}
}
