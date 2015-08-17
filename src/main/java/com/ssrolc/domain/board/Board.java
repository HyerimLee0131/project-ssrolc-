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
	private int rowBlockSize;
	private int pageBlockSize;
	private Boolean boardCategoryEnable;
}
