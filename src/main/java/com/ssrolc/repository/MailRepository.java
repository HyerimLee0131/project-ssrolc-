package com.ssrolc.repository;

import java.util.Map;



@PrimaryRepositoryAnnoInterface
public interface MailRepository {
	
	/*
	 * 메일인증한사람 등록하기
	 * */
	public void insertMailAuth(Map<String,Object> map);
	
	/*
	 * 인증키 일치,불일치 확인하기위해
	 * */
	public String findAuthKey(Map<String, Object> map);

}
