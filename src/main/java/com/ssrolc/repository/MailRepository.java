package com.ssrolc.repository;

import java.util.Map;



@PrimaryRepositoryAnnoInterface
public interface MailRepository {
	
	/*
	 * 메일인증한사람 등록하기
	 * */
	public void insertMailAuth(Map<String,Object> map);

}
