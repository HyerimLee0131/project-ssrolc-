package com.ssrolc.exception;

import org.springframework.http.HttpStatus;


public class BoardNotFoundException extends ResourceNotFoundException{
	private final String boardTable;
	
	public BoardNotFoundException(String boardTable) {
		this.boardTable = boardTable;
	}

	public String getBoardTable() {
		return boardTable;
	}
	
	@Override
	public String getError() {
		return "Board " + HttpStatus.NOT_FOUND.getReasonPhrase();
	}
	
	@Override
	public Object[] getArgs() {
		return new Object[]{ boardTable };
	}
}
