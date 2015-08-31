package com.ssrolc.controller.ssrolcfront;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssrolc.domain.CenterSearch.CenterSearch;
import com.ssrolc.service.CenterSearchService;
import com.ssrolc.service.FranchiseService;

@Controller
public class FranchiseFrontController {

	@Autowired
	private FranchiseService franchiseService;
	
	@Autowired
	private CenterSearchService centerSearchService;
	
	//가맹지사 메인페이지
	@RequestMapping(value={"/ssrolcfront/franchise/search"} , method =  { RequestMethod.GET, RequestMethod.HEAD })
	public String list(Model model) {
				
		// 해더에 스크립트 추가
		List<String> headerScript = new ArrayList<>();
		headerScript.add("ssrolcfront/franchise/franchiseSearch");
		model.addAttribute("headerScript",headerScript);
		
			return "ssrolcfront/franchise/index";
		}
	
//	//리스트 검색조건 추가
//		@RequestMapping(value={"/ssrolcfront/franchise/{doName}/{guName}"},method = {RequestMethod.GET,RequestMethod.HEAD})
//		@ResponseBody
//		public ResponseEntity<Map<String,Object>> franchiseListJson(@PathVariable String doName
//																	,@PathVariable String guName){
//			// 시/도 셀렉트 박스 리스트
//			List<String> doList = centerSearchService.getDos("","","","6");
//			// 해더에 스크립트 추가
//			List<String> headerScript = new ArrayList<>();
//			headerScript.add("ssrolcfront/franchise/franchiseSearch");
//			model.addAttribute("headerScript",headerScript);
//			model.addAttribute("doList", doList);
//			
//			//검색어가 없으면 전체 검색
//			if(doName.equals("all")) {
//				doName = "";
//			}
//			//리스트 받아오기
//			Map<String,Object> map = new HashMap<>();
//			map.put("centers1",centerSearchService.getCenters(doName));
//
//			//완료
//			map.put("result", "success");
//
//			return ResponseEntity.ok(map);
//		}
	
	
}
