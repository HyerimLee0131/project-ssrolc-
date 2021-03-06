package com.ssrolc.domain.common;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*
 * 유저권한
 * 만든이:김순호
 * 일자:2015-07-28
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class UserRole {
	private String userId;
	private String authority;
	private Timestamp regDate;
	private Timestamp updateDate;
}
