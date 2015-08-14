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
	private int fileType;
	private String fileFormat;
	private String regId;
	private String regIp;
	private Timestamp regDate;
}
