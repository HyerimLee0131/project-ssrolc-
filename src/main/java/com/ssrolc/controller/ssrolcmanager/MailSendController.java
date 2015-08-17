package com.ssrolc.controller.ssrolcmanager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssrolc.service.MailService;


@Controller
public class MailSendController {
	private static final Logger logger = LoggerFactory.getLogger(MailSendController.class);
	
	@Autowired
	private MailService mailService;
	
	@RequestMapping(value={"/ssrolcmanager/mail"})
	public String main(){
		mailService.sendMail();
		return "ssrolcmanager/index";
	}
}
