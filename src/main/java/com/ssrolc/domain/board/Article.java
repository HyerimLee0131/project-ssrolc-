package com.ssrolc.domain.board;

import java.sql.Timestamp;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.ssrolc.utils.CommonUtils;

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
	@Getter(value=AccessLevel.NONE)
	private Timestamp regDate;
	private Timestamp updateDate;

	public String getRegDate() {
		return CommonUtils.timestampToString(regDate,"yyyy-MM-dd");
	}
	
	public String getRegDate(String format) {
		
		return CommonUtils.timestampToString(regDate,format);
	}

}
