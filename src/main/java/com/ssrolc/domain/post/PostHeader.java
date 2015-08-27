package com.ssrolc.domain.post;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

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
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"requestMsgId","responseMsgId","responseTime","successYN","returnCode","errMsg"
		,"totalCount","countPerPage","totalPage","currentPage"})
public class PostHeader {
	private String requestMsgId;
	private String responseMsgId;
	private String responseTime;
	private String successYN;
	private String returnCode;
	private String errMsg;
	private int totalCount;
	private int countPerPage;
	private int totalPage;
	private int currentPage;
}
