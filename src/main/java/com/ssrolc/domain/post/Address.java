package com.ssrolc.domain.post;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import lombok.Data;


@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Address {
	@XmlAttribute
	private String zipNo;
	@XmlAttribute
	private String lnmAdres;
	@XmlAttribute
	private String rnAdres;
}
