package com.ssrolc.domain.prmedia;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.ssrolc.utils.CommonUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Prmedia {
	private int aidx;
	private String prTitle;
	private String makeTime;
	private String thumnailName;
	private String thumnailRealName;
	private String thumnailSize;
	private int hitCnt;
	private String mediaLinkUrl;
	private String mediaLocation;
	
	private Timestamp updateDate;
	private String updateId;
	private Timestamp regDate;
	private String regId;
	private String regIp;
	
	public String getRegDate(String format) {
		
		return CommonUtils.timestampToString(regDate,format);
	}
}


