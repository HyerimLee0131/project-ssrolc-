package com.ssrolc.config;

import lombok.Data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.ssrolc.controller.ssrolcfront.DisclosureFrontController;

@Data
@Configuration
@ConfigurationProperties(prefix = "mail")
public class MailSendConfig {
	private static final Logger logger = LoggerFactory.getLogger(MailSendConfig.class);
	private String host;

	
	@Bean
	public JavaMailSender mailSender(){
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(host);
		return mailSender;
	}
}
