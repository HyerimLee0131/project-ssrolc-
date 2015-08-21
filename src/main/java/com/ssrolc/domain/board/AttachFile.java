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
public class AttachFile {
	private int attachFileNo;
	private String boardTable;
	private int articleNo;
	private boolean editFlag;
	private int sort;
	private String fileName;
	private String convertFileName;
	private int downloadCnt;
	private int fileSize;
	private int fileWidth;
	private int fileHeight;
	private String fileType;
	private String fileFormat;
	private String regId;
	private String regIp;
	private Timestamp regDate;
	
	public AttachFile(String boardTable, int articleNo, boolean editFlag,
			int sort, String fileName, String convertFileName, int downloadCnt,
			int fileSize, int fileWidth, int fileHeight, String fileType,
			String fileFormat, String regId, String regIp, Timestamp regDate) {
		super();
		this.boardTable = boardTable;
		this.articleNo = articleNo;
		this.editFlag = editFlag;
		this.sort = sort;
		this.fileName = fileName;
		this.convertFileName = convertFileName;
		this.downloadCnt = downloadCnt;
		this.fileSize = fileSize;
		this.fileWidth = fileWidth;
		this.fileHeight = fileHeight;
		this.fileType = fileType;
		this.fileFormat = fileFormat;
		this.regId = regId;
		this.regIp = regIp;
		this.regDate = regDate;
	}
}
