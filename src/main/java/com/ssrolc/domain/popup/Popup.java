package com.ssrolc.domain.popup;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
	private Timestamp startDate;
	private Timestamp endDate;
	private String state;
	private Timestamp updateDate;
	private String updateId;
	private Timestamp regDate;	
	private String regId;
	private String regIp;
}
