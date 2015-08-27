package com.ssrolc.domain.post;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class PostHeader {
	@XmlAttribute
	private String requestMsgId;
	@XmlAttribute
	private String responseMsgId;
	@XmlAttribute
	private String responseTime;
	@XmlAttribute
	private String successYN;
	@XmlAttribute
	private String returnCode;
	@XmlAttribute
	private String errMsg;
	@XmlAttribute
	private int totalCount;
	@XmlAttribute
	private int countPerPage;
	@XmlAttribute
	private int totalPage;
	@XmlAttribute
	private int currentPage;
}
