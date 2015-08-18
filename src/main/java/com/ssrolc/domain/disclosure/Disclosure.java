package com.ssrolc.domain.disclosure;

import java.sql.Timestamp;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import com.ssrolc.utils.CommonUtils;

/*
 * Disclosure.java
 * 작성자 : 이혜림(홈페이지운영팀)
 * */

@Data
public class Disclosure {
	private int aidx;
	private String email;
	private String memName;
	private String deptArea1;
	private String deptArea2;
	private String deptType;
	private String phone;
	private int zip1;
	private int zip2;
	private String addressDtl01;
	private String addressDtl02;
	@Getter(value=AccessLevel.NONE)
	private Timestamp regDate;
	
	public String getRegDate() {
		
		return CommonUtils.timestampToString(regDate,"yyyy-MM-dd HH:mm:ss");
	}
	
}
