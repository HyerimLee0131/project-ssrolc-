package com.ssrolc.domain.post;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

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
@XmlRootElement(name="NewAddressListResponse")
public class Post {
	@XmlElement(name="cmmMsgHeader")
	private PostHeader postHeader;
	@XmlElement(name="newAddressListAreaCdSearchAll")
	private List<Address> addressList;
	
	private int startPageNum;
	private int endPageNum;
}
