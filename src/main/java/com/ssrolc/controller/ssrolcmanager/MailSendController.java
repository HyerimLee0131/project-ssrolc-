package com.ssrolc.controller.ssrolcmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ssrolc.service.MailService;


@Controller
public class MailSendController {
	private static final Logger logger = LoggerFactory.getLogger(MailSendController.class);
	
	@Autowired
	private MailService mailService;

	@RequestMapping(value={"/disclosure"})
	public String disclosure(Model model){
		
		//해더에 스크립트 추가
		List<String> headerScript = new ArrayList<>();
		headerScript.add("ssrolcfront/disclosure/list");
		
		model.addAttribute("headerScript",headerScript);
		
		return "disclosure/index";
	}	
	
	@RequestMapping(value={"/disclosure/mail"},method =  { RequestMethod.POST})
	@ResponseBody
	public ResponseEntity<Map<String, Object>> main(HttpServletRequest request,String pMemName,String pEmailId, String pEmailAdd1){

		String hostName = request.getServerName();
		HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		
		String jslIp = req.getHeader("X-FORWARDED-FOR");
        if (jslIp == null)
        	jslIp = req.getRemoteAddr();
		
		String authKey = mailService.sendMail(pMemName,pEmailId,pEmailAdd1,hostName);
		String mailAddress = pEmailId + "@" + pEmailAdd1;
		mailService.insertMailAuth(pMemName,mailAddress,authKey,jslIp);
		
		Map<String, Object> map = new HashMap<>();
		map.put("result","MailSendOk");
		return ResponseEntity.ok(map); 
	}
	
	@RequestMapping(value={"/disclosure/mail"},method =  { RequestMethod.GET,RequestMethod.HEAD})
	@ResponseBody
	public ResponseEntity<Map<String, Object>> AuthKey(String authKey,String memName,String email){
		String authInfo = mailService.getAuthInfo(authKey,memName,email);
		Map<String, Object> map = new HashMap<>();
		map.put("result",authInfo);
		return ResponseEntity.ok(map); 
	
	}
	
}
