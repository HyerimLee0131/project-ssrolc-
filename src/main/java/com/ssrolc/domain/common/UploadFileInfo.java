package com.ssrolc.domain.common;

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
public class UploadFileInfo {
	private String originalFilename;
	private String convertFileName;
	private int width;
	private int height;
	private int size;
	/*
	 * image or doc
	 */
	private String fileType;
	/*
	 * True:썸네일 or False:메인
	 */
	private boolean thumbType;
}
