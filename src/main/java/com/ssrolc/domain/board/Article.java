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
public class Article {
	private int articleNo;
	private String boardTable;
	private String categoryCode;
	private String title;
	private String content;
	private int hit;
	private int fileCnt;
	private int imageCnt;
	private String etc1;
	private String etc2;
	private String etc3;
	private boolean useEnable;
	private boolean deleteFlag;
	private String regId;
	private String regIp;
	private Timestamp regDate;
	private Timestamp updateDate;
}
