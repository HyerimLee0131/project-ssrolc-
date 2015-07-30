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
	private boolean userEnable;
}
