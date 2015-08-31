package com.ssrolc.domain.disclosure;

import java.sql.Timestamp;

import com.ssrolc.utils.CommonUtils;

import lombok.Data;

/*
 * DisclosureEmailAuth.java
 * 작성자 : 이혜림(홈페이지운영팀)
 * */

@Data
public class DisclosureEmailAuth {
	private int aidx;
	private String authKey;
	private String email;
	private String memName;
	private Timestamp regDate;
	private String regIp;
	
	public String getRegDate(String format) {
		
		return CommonUtils.timestampToString(regDate,format);
	}
	
	
}
