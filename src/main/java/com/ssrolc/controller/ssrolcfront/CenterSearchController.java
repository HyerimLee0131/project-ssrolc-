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

import com.ssrolc.domain.CenterSearch.CenterSearch;
import com.ssrolc.service.CenterSearchService;


@Controller
public class CenterSearchController {
	private static final Logger logger = LoggerFactory.getLogger(CenterSearchController.class);

	@Autowired
	private CenterSearchService centerSearchService;

	//리스트
	@RequestMapping(value={"/ssrolcfront/centersearch"},method = {RequestMethod.GET,RequestMethod.HEAD})
	public String centerSearchList(Model model){
		logger.debug("====================================centerSearch List");

		model.addAttribute("title","재능스스로 러닝센터");

		//시/도 셀렉트 박스 리스트
		List<String> doList = centerSearchService.getDos("","","","1");

		//해더에 스크립트 추가
		List<String> headerScript = new ArrayList<>();
		headerScript.add("ssrolcfront/centerSearch");
		model.addAttribute("headerScript",headerScript);
		model.addAttribute("doList", doList);
		

		return "ssrolcfront/centerSearch/list";
	}

	//리스트 검색조건 추가
	@RequestMapping(value={"/ssrolcfront/centersearch/{doName}"},method = {RequestMethod.GET,RequestMethod.HEAD})
	@ResponseBody
	public ResponseEntity<Map<String,Object>> centerSearchListJson(@PathVariable String doName){
		logger.debug("====================================centerSearch List area:"+doName);

		//검색어가 없으면 전체 검색
		if(doName.equals("all")) {
			doName = "";
		}

		//직영센터 예외처리 생성
		CenterSearch byulnae = new CenterSearch("","별내직영학원","","031","523","2702","경기도 남양주시 별내동 1097-1번지 4층 스스로러닝센터");
		CenterSearch homaesil = new CenterSearch("","호매실직영학원","","031","546","8863","경기도 수원시 권선구 금곡로 102번길 15 비즈웍스 5차 515호");
		CenterSearch gyerong = new CenterSearch("","계룡직영학원","","042","841","3171","충청남도 논산시 엄사면 엄사리 362-2");
		CenterSearch sejong = new CenterSearch("","세종직영학원","","044","864","2332","세종시 보듬3로 92 해피라움 2동 603호");
		CenterSearch myungji = new CenterSearch("","명지직영학원","","051","714","2018","부산시 명지동 3238-12번지 NC빌딩 501호");
		CenterSearch samhwa = new CenterSearch("","삼화직영학원","","064","803","2088","제주시 화북1동 1019-3 주립빌딩 3층");

		//리스트 받아오기
		Map<String,Object> map = new HashMap<>();
		map.put("centers1",centerSearchService.getCenters(doName));

		//직영센터 예외처리 입력
		List<CenterSearch> exceptionList = new ArrayList<CenterSearch>();

		if(doName.equals("")) {	//전체
			exceptionList.add(byulnae);
			exceptionList.add(homaesil);
			exceptionList.add(gyerong);
			exceptionList.add(sejong);
			exceptionList.add(myungji);
			exceptionList.add(samhwa);
		} else {
			if(doName.equals("경기")) {
				exceptionList.add(byulnae);
				exceptionList.add(homaesil);
			} else if(doName.equals("충남")) {
				exceptionList.add(gyerong);
			} else if(doName.equals("세종")) {
				exceptionList.add(sejong);
			} else if(doName.equals("부산")) {
				exceptionList.add(myungji);
			} else if(doName.equals("제주")) {
				exceptionList.add(samhwa);
			}
		}

		if(!exceptionList.isEmpty()) {
			map.put("centers2",exceptionList);
		}

		//완료
		map.put("result", "success");

		return ResponseEntity.ok(map);
	}
}
