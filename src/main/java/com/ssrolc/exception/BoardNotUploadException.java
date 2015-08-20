package com.ssrolc.exception;

import org.springframework.http.HttpStatus;

public class BoardNotUploadException extends ResourceNotFoundException {
	private final String boardTable;
	
	public BoardNotUploadException(String boardTable) {
		this.boardTable = boardTable;
	}

	public String getBoardTable() {
		return boardTable;
	}
	
	@Override
	public String getError() {
		return "BoardUpload " + HttpStatus.NOT_ACCEPTABLE.getReasonPhrase();
	}
	
	@Override
	public Object[] getArgs() {
		return new Object[]{ boardTable };
	}
}
