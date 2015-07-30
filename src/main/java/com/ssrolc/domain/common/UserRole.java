package com.ssrolc.domain.common;

import java.sql.Timestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/*
 * 유저권한
 * 만든이:김순호
 * 일자:2015-07-28
 */

@Data
@ToString
@EqualsAndHashCode
public class UserRole {
	private String userId;
	private String authority;
	private Timestamp regDate;
	private Timestamp updateDate;
}
