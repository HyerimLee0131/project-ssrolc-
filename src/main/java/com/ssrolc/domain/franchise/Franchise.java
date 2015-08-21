package com.ssrolc.domain.franchise;

import java.sql.Timestamp;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import com.ssrolc.utils.CommonUtils;

/*
 * Franchise.java
 * 작성자 : 이혜림(홈페이지운영팀)
 * */

@Data
public class Franchise {
	private String jslctable;
	private int jslcId;
	private String inType;
	private String jslcName;
	private String jslcZip1;
	private String jslcZip2;
	private String jslcAddr1;
	private String jslcAddr2;
	private String jslcArea1;
	private String jslcArea2;
	private String jslcType;
	private String jslcSubject;
	private String jslcDate;
	private String jslcTime1;
	private String jslcTime2;
	private String jslcCP1;
	private String jslcCP2;
	private String jslcCP3;
	private String jslcounseling;
	private String jslComments;
	private Timestamp jslipDatetime;
	private String jslIp;

}
