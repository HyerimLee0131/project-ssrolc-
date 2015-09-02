package com.ssrolc.utils.mail;

import java.util.Map;


public interface RegistrationNotifier {
	public void sendMail(Map model,String pMemName,String pEmailId,String pEmailAdd1,String hostName);
}
