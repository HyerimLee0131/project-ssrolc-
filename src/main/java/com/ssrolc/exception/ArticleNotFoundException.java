package com.ssrolc.exception;

import org.springframework.http.HttpStatus;

public class ArticleNotFoundException extends ResourceNotFoundException{
private final String boardTable;
private final int articleNo;
	
	public ArticleNotFoundException(String boardTable,int articleNo) {
		this.boardTable = boardTable;
		this.articleNo = articleNo;
	}

	public String getBoardTable() {
		return boardTable;
	}
	
	public int getArticleNo() {
		return articleNo;
	}

	@Override
	public String getError() {
		return "Article " + HttpStatus.NOT_FOUND.getReasonPhrase();
	}
	
	@Override
	public Object[] getArgs() {
		return new Object[]{ boardTable,articleNo};
	}
}
