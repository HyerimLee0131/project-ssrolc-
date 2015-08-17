package com.ssrolc.exception;

import org.springframework.http.HttpStatus;


public class BoardCategoryNotFoundException extends ResourceNotFoundException{
	private final String boardTable;
	
	public BoardCategoryNotFoundException(String boardTable) {
		this.boardTable = boardTable;
	}

	public String getBoardTable() {
		return boardTable;
	}
	
	@Override
	public String getError() {
		return "BoardCategory " + HttpStatus.NOT_FOUND.getReasonPhrase();
	}
	
	@Override
	public Object[] getArgs() {
		return new Object[]{ boardTable };
	}
}
