package com.ssrolc.exception;

import org.springframework.http.HttpStatus;

public class ArticleNotAddException extends ResourceNotFoundException{
private final String boardTable;
	
	public ArticleNotAddException(String boardTable) {
		this.boardTable = boardTable;
	}

	public String getBoardTable() {
		return boardTable;
	}
	

	@Override
	public String getError() {
		return "Article " + HttpStatus.NOT_ACCEPTABLE.getReasonPhrase();
	}
	
	@Override
	public Object[] getArgs() {
		return new Object[]{ boardTable};
	}
}
