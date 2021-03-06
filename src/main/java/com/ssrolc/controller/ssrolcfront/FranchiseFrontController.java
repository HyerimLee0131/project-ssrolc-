package com.ssrolc.controller.ssrolcfront;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;
import com.ssrolc.domain.franchise.Franchise;
import com.ssrolc.service.CenterSearchService;
import com.ssrolc.service.FranchiseService;
import com.ssrolc.service.MailService;

@Controller
public class FranchiseFrontController {

	@Autowired
	private FranchiseService franchiseService;
	
	@Autowired
	private CenterSearchService centerSearchService;
	
	@Autowired
	private MailService mailService;
	
	@Value("${mail.faqadminmail}")
	private String adminAddress;
	
	//가맹지사 메인페이지
	@RequestMapping(value={"/ssrolcfront/franchise/search"} , method =  { RequestMethod.GET, RequestMethod.HEAD })
	public String list(Model model) {
		
		// 시/도 셀렉트 박스 리스트
		List<String> doList = centerSearchService.getDos("","","","1");		
		
		// 해더에 스크립트 추가
		List<String> headerScript = new ArrayList<>();
		headerScript.add("ssrolcfront/franchise/franchiseSearch");
		model.addAttribute("headerScript",headerScript);
		model.addAttribute("doList", doList);
		
			return "ssrolcfront/franchise/index";
		}
	
	@RequestMapping(value={"/ssrolcfront/franchise/{doName}"},method={RequestMethod.GET,RequestMethod.HEAD})
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getGugun(@PathVariable String doName){
		
		// 구/군 셀렉트 박스 리스트
		
		Map<String,Object> map = new HashMap<>();
		map.put("guGunList",franchiseService.getGugun(doName,"","","2"));	
		
		return ResponseEntity.ok(map);
	}
	
	
	//리스트 검색조건 추가
		@RequestMapping(value={"/ssrolcfront/franchise/{doName}/{si}"},method = {RequestMethod.GET,RequestMethod.HEAD})
		@ResponseBody
		public ResponseEntity<Map<String,Object>> franchiseListJson(@PathVariable String doName
																	,@PathVariable String si){
			//리스트 받아오기
			Map<String,Object> map = new HashMap<>();
			map.put("franchises",franchiseService.getFranchises(doName,si,"","6"));

			map.put("result", "success");
			return ResponseEntity.ok(map);
		}
		
		//가맹문의 페이지
		@RequestMapping(value={"/ssrolcfront/franchise/faq"} , method =  { RequestMethod.GET, RequestMethod.HEAD })
		public String write(Model model) {
				
			List<String> cityList = franchiseService.getFranchiseCityList();
			// 해더에 스크립트 추가
			List<String> headerScript = new ArrayList<>();
			headerScript.add("ssrolcfront/franchise/view");
			model.addAttribute("headerScript",headerScript);
			model.addAttribute("cityList", cityList);
			
				return "ssrolcfront/franchise/faq";
		}
		//가맹문의등록 insert
		@RequestMapping(value={"/ssrolcfront/franchise/faq"},method ={RequestMethod.POST})
			public ResponseEntity<Map<String, Object>> insertFaq(HttpServletRequest req,Franchise franchise){	

				String jslIp = req.getHeader("X-FORWARDED-FOR");
		        if (Strings.isNullOrEmpty(jslIp)) {
		        	jslIp = req.getRemoteAddr();
		        }
		        franchise.setJslIp(jslIp);
		        franchise.setJslcounseling("0");
		        franchiseService.insertFranchise(franchise);
		        
		        Map<String, Object> map  = mailService.sendApplicationFormMail(franchise,adminAddress);
		        
				return ResponseEntity.ok(map); 
			}
	
	
}
