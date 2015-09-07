package com.ssrolc.domain.disclosure;

import java.sql.Timestamp;

import lombok.Data;

import com.ssrolc.utils.CommonUtils;

/*
 * IgnoreEmail.java
 * 작성자 : 이혜림(홈페이지운영팀)
 * */

@Data
public class IgnoreEmail {
	private int aidx;
	private String email;
	private String reason;
	private Timestamp regDate;
	private String regIp;
	
	public String getRegDate(String format) {
		
		return CommonUtils.timestampToString(regDate,format);
	}
	
	
}
