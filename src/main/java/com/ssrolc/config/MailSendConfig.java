package com.ssrolc.config;

import lombok.Data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.ssrolc.controller.ssrolcmanager.BoardController;

@Data
@Configuration
@ConfigurationProperties(prefix = "mail")
public class MailSendConfig {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	private String host;
	private int port;
	private String username;
	private String password;
	
	@Bean
	public JavaMailSender mailSender(){
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		logger.debug(username+"==========================");
		mailSender.setHost(host);
		mailSender.setUsername(username);
		mailSender.setPassword(password);
		return mailSender;
	}
}
