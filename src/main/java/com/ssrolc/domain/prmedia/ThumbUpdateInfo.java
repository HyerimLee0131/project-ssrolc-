package com.ssrolc.domain.prmedia;

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
public class ThumbUpdateInfo {
	private String fileName;
	private String fileRealName;
	private int		fileWidth;
	private int		fileHeight;
	private int 	filelSize;
	
	private String thumnailName;
	private int		thumbWidth;
	private int		thumbHeight;
	private int thumnailSize;
	
	
}
