package com.ssrolc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.ssrolc.utils.mail.RegistrationNotifier;

@Service
public class MailService implements RegistrationNotifier {
	
	@Autowired
    private MailSender mailSender;
	
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Override
	public void sendMail() {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setSubject("[Simple]스스로러닝센터입니다.");
		message.setFrom("mail@jei.com");
		message.setText("안녕하세요 스스로러닝센터입니다.");
		message.setTo("sayhyerim@jei.com");
		try {
			mailSender.send(message);
		} catch (MailException ex) {
			ex.printStackTrace();
		}
	}
}
