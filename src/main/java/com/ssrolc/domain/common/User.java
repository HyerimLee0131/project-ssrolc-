package com.ssrolc.domain.common;

import java.sql.Timestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/*
 * 유저
 * 만든이:김순호
 * 일자:2015-07-28
 */
@Data
@ToString
@EqualsAndHashCode
public class User {
	private String userId;
	private String userPassword;
	private String userName;
	private String userNick;
	private String userEncodeKey;
	private Timestamp userRegDate;
	private Timestamp userLeaveDate;
	private Boolean userEnable;
	
	public User(String userId, String userPassword, String userName,
			String userNick, String userEncodeKey, Timestamp userRegDate,
			Timestamp userLeaveDate,Boolean userEnable) {
		super();
		this.userId = userId;
		this.userPassword = userPassword;
		this.userName = userName;
		this.userNick = userNick;
		this.userEncodeKey = userEncodeKey;
		this.userRegDate = userRegDate;
		this.userLeaveDate = userLeaveDate;
		this.userEnable = userEnable;
	}
}
