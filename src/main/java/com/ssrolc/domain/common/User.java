package com.ssrolc.domain.common;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*
 * 유저
 * 만든이:김순호
 * 일자:2015-07-28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
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
}
