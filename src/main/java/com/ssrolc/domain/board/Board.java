package com.ssrolc.domain.board;

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
public class Board {
	private String boardTable;
	private String boardName;
	private int frontRowBlockSize;
	private int frontPageBlockSize;
	private int managerRowBlockSize;
	private int managerPageBlockSize;
	private boolean boardCategoryEnable;
	private boolean boardFileUploadEnable;
	private String boardFileUploadType;
	private int thumnailWidth;
	private boolean editorImageUploadEnable;
}
