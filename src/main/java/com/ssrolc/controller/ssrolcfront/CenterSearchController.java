package com.ssrolc.controller.ssrolcfront;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssrolc.service.CenterSearchService;


@Controller
public class CenterSearchController {
	private static final Logger logger = LoggerFactory.getLogger(CenterSearchController.class);

	@Autowired
	private CenterSearchService ceterSearchService;

	//리스트
	@RequestMapping(value={"/ssrolcfront/centerSearch"},method = {RequestMethod.GET,RequestMethod.HEAD})
	public String centerSearchList(Model model){
		logger.debug("====================================centerSearch List");

		model.addAttribute("title","재능스스로 러닝센터");

		//시/도 셀렉트 박스 리스트
		List<String> doList = ceterSearchService.getDos("","","","1");

		//해더에 스크립트 추가
		List<String> headerScript = new ArrayList<>();
		headerScript.add("ssrolcfront/centerSearch");
		model.addAttribute("headerScript",headerScript);
		model.addAttribute("doList", doList);

		return "ssrolcfront/centerSearch/list";
	}

	//리스트 검색조건 추가
	@RequestMapping(value={"/ssrolcfront/centerSearch/{doName}"},method = {RequestMethod.GET,RequestMethod.HEAD})
	@ResponseBody
	public ResponseEntity<Map<String,Object>> centerSearchListJson(@PathVariable String doName){
		logger.debug("====================================centerSearch List area:"+doName);

		//검색어가 없으면 전체 검색
		if(doName.equals("all")) {
			doName = "";
		}

		Map<String,Object> map = new HashMap<>();

		//가맹센터 리스트 받아오기
		map.put("centers1",ceterSearchService.getCenters(doName));
		//직영센터 리스트 받아오기
		map.put("centers2",ceterSearchService.getDirectCenters(doName));

		//완료
		map.put("result", "success");

		return ResponseEntity.ok(map);
	}
}
