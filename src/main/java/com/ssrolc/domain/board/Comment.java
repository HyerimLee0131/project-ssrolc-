package com.ssrolc.domain.board;

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
public class Comment {
	private int commonNo;
	private String boardTable;
	private int articleNo;
	private String content;
	private boolean deleteFlag;
	private String regId;
	private String regIp;
	private Timestamp regDate;
	private Timestamp updateDate;
}
