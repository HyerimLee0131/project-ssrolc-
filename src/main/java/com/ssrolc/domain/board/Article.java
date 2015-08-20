package com.ssrolc.domain.board;

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
	
	public String getRegDate(String format) {
		
		return CommonUtils.timestampToString(regDate,format);
	}

	public Article(String boardTable, String categoryCode, String title,
			String content, int hit, int fileCnt, int imageCnt, String etc1,
			String etc2, String etc3, boolean useEnable, boolean deleteFlag,
			String regId, String regIp, Timestamp regDate, Timestamp updateDate) {
		super();
		this.boardTable = boardTable;
		this.categoryCode = categoryCode;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.fileCnt = fileCnt;
		this.imageCnt = imageCnt;
		this.etc1 = etc1;
		this.etc2 = etc2;
		this.etc3 = etc3;
		this.useEnable = useEnable;
		this.deleteFlag = deleteFlag;
		this.regId = regId;
		this.regIp = regIp;
		this.regDate = regDate;
		this.updateDate = updateDate;
	}

}
