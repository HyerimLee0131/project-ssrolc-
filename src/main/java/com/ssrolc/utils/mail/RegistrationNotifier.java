package com.ssrolc.utils.mail;

import java.util.Map;



public interface RegistrationNotifier {
	public Map<String, Object> sendMail(String pMemName,String pEmailId,String pEmailAdd1,String hostName);
}
