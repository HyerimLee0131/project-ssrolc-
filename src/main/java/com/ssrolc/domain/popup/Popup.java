package com.ssrolc.domain.popup;

import java.sql.Timestamp;

import com.ssrolc.utils.CommonUtils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Popup {
	private int aidx;
	private String cookie_id;
	private String name;
	private String size_width;
	private String size_height;
	private String location_top;
	private String location_left;
	private String fileName;
	@Getter(value=AccessLevel.NONE)
	private Timestamp startDate;
	@Getter(value=AccessLevel.NONE)
	private Timestamp endDate;
	private String state;
	private Timestamp updateDate;
	private String updateId;
	@Getter(value=AccessLevel.NONE)
	private Timestamp regDate;	
	private String regId;
	private String regIp;

	public String getStartDate() {
		return CommonUtils.timestampToString(startDate,"yyyy-MM-dd");
	}

	public String getEndDate() {
		return CommonUtils.timestampToString(endDate,"yyyy-MM-dd");
	}

	public String getRegDate() {
		return CommonUtils.timestampToString(regDate,"yyyy-MM-dd");
	}
}
