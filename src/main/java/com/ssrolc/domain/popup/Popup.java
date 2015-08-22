package com.ssrolc.domain.popup;

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
public class Popup {
	private int aidx;
	private String cookie_id;
	private String name;
	private String size_width;
	private String size_height;
	private String location_top;
	private String location_left;
	private String fileName;
	private String startDate;
	private String endDate;
	private String state;
	private Timestamp updateDate;
	private String updateId;
	private Timestamp regDate;	
	private String regId;
	private String regIp;

/*	public String getStartDate(String format) {
		return CommonUtils.timestampToString(startDate,format);
	}

	public String getEndDate(String format) {
		return CommonUtils.timestampToString(endDate,format);
	}
*/
	public String getRegDate(String format) {
		return CommonUtils.timestampToString(regDate,format);
	}

	public Popup(String cookie_id, String name, String size_width,
			String size_height, String location_top, String location_left, String fileName, String startDate,
			String endDate, String state, //Timestamp updateDate, String updateId,
			Timestamp regDate, String regId, String regIp) {
		super();
		this.cookie_id = cookie_id;
		this.name = name;
		this.size_width = size_width;
		this.size_height = size_height;
		this.location_top = location_top;
		this.location_left = location_left;
		this.fileName = fileName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.state = state;
		//this.updateDate = updateDate;
		//this.updateId = updateId;
		this.regDate = regDate;
		this.regId = regId;
		this.regIp = regIp;
	}
}
