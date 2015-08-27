package com.ssrolc.domain.post;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="NewAddressListResponse")
public class Post {
	@XmlElement(name="cmmMsgHeader")
	private PostHeader postHeader;
	@XmlElement(name="newAddressListAreaCdSearchAll")
	private List<Address> addressList;
}
