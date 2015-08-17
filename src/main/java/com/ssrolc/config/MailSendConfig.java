package com.ssrolc.config;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Data
@Configuration
@ConfigurationProperties(prefix = "mail", locations = {"classpath:application.properties"})
public class MailSendConfig {
	
	private String host;
	private int port;
	private String username;
	private String password;
	
	@Bean
	public JavaMailSender mailSender(){
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(getHost());
		mailSender.setUsername(getUsername());
		mailSender.setPassword(getPassword());
		return mailSender;
	}
}
