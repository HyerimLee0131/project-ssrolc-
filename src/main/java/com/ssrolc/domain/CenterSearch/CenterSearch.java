package com.ssrolc.domain.CenterSearch;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*
 * CenterSearch.java
 * 작성자 : 변준영(홈페이지운영팀)
 * 2015/08/21
 * */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class CenterSearch {
	private String deptId1;
	private String deptName;
	private String homeUrl;
	private String tel1;
	private String tel2;
	private String tel3;
	private String addr;
}
