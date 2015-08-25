package com.ssrolc.controller.ssrolcmanager;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssrolc.service.MailService;


@Controller
public class MailSendController {
	private static final Logger logger = LoggerFactory.getLogger(MailSendController.class);
	
	@Autowired
	private MailService mailService;

	@RequestMapping(value={"/disclosure"})
	public String disclosure(Model model){
		
		return "disclosure/index";
	}	
	
	@RequestMapping(value={"/disclosure/mail"})
	public String main(HttpServletRequest request,String pEmailId, String pEmailAdd1,String pEmailAdd2){
		String hostName = request.getServerName();
		
		mailService.sendMail(pEmailId,pEmailAdd1,pEmailAdd2,hostName);
		
		return "redirect:/disclosure"; 
	}	
}
