package com.ssrolc.controller.ssrolcfront;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;
import com.ssrolc.domain.disclosure.Disclosure;
import com.ssrolc.service.DisclosureService;
import com.ssrolc.service.FranchiseService;
import com.ssrolc.service.MailService;


@Controller
public class DisclosureFrontController {
	private static final Logger logger = LoggerFactory.getLogger(DisclosureFrontController.class);
	
	@Autowired
	private MailService mailService;
	@Autowired
	private FranchiseService franchiseService;
	@Autowired
	private DisclosureService disclosureService;

	//정보공개서 페이지
	@RequestMapping(value={"/ssrolcfront/disclosure"},method =  { RequestMethod.GET,RequestMethod.HEAD})
	public String disclosure(Model model){
		List<String> cityList = franchiseService.getFranchiseCityList();
		//해더에 스크립트 추가
		List<String> headerScript = new ArrayList<>();
		headerScript.add("ssrolcfront/disclosure/list");
		model.addAttribute("headerScript",headerScript);
		model.addAttribute("cityList", cityList);
		
		return "ssrolcfront/disclosure/index";
	}
	/*
	 * 이메일인증한사람 등록
	 * */
	@RequestMapping(value={"/ssrolcfront/disclosure"},method ={RequestMethod.POST})
	@ResponseBody
	public ResponseEntity<Map<String,Object>> insert(HttpServletRequest req,Disclosure disclosure){		
			//ip넣기
			String regIp = req.getHeader("X-FORWARDED-FOR");
	        if (Strings.isNullOrEmpty(regIp)){
	        	regIp = req.getRemoteAddr();
	        }	
	        disclosure.setRegIp(regIp);
	        
			disclosureService.setDisclosure(disclosure);
			
			Map<String,Object> map = new HashMap<>();
			map.put("ebookUrl","/public/js/disclosure/disclosure/EBook.htm");
			
			return ResponseEntity.ok(map); 
		}
	
	
	//이메일 보내기
	@RequestMapping(value={"/ssrolcfront/disclosure/email"},method =  { RequestMethod.POST})
	@ResponseBody
	public ResponseEntity<Map<String, Object>> main(HttpServletRequest request,String pMemName,String pEmailId, String pEmailAdd1){

		String hostName = request.getServerName();

		String jslIp = request.getHeader("X-FORWARDED-FOR");
        if (Strings.isNullOrEmpty(jslIp)){
        	jslIp = request.getRemoteAddr();
        }
		String authKey = mailService.sendMail(pMemName,pEmailId,pEmailAdd1,hostName);
		String mailAddress = pEmailId + "@" + pEmailAdd1;
		disclosureService.insertMailAuth(pMemName,mailAddress,authKey,jslIp);
		
		Map<String, Object> map = new HashMap<>();
		map.put("result","MailSendOk");
		return ResponseEntity.ok(map); 
	}
	//인증번호 체크
	@RequestMapping(value={"/ssrolcfront/disclosure/email"},method =  { RequestMethod.GET,RequestMethod.HEAD})
	@ResponseBody
	public ResponseEntity<Map<String, Object>> AuthKey(String authKey,String memName,String email){
		String result  = disclosureService.isDisclosureEmailAuth(authKey,memName,email);
		Map<String, Object> map = new HashMap<>();
		if("authKeyOk".equals(result)){
			map.put("disclosure",disclosureService.getDisclosureInfo(memName,email));
		}
		map.put("result",result);
		return ResponseEntity.ok(map); 
	
	}
	
}
