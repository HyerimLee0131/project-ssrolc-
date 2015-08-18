package com.ssrolc.controller.ssrolcmanager;


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

import com.ssrolc.exception.PopupNotFoundException;
import com.ssrolc.service.PopupService;
import com.ssrolc.utils.PageUtil;


@Controller
public class PopupController {
	private static final Logger logger = LoggerFactory.getLogger(PopupController.class);
	
	@Autowired
	private PopupService popupService;
	
	//리스트
	@RequestMapping(value={"/ssrolcmanager/popups"},method = {RequestMethod.GET,RequestMethod.HEAD})
	public String popupList(Model model){
		logger.debug("popup List");
		
		model.addAttribute("title","러닝센터관리자 팝업관리");
		
		//해더에 스크립트 추가
		List<String> headerScript = new ArrayList<>();
		headerScript.add("common");
		headerScript.add("ssrolcmanager/popups/admin_list");
		model.addAttribute("headerScript",headerScript);
		
		return "ssrolcmanager/popups/popupList";
	}
	
	//리스트 paging 추가
	@RequestMapping(value={"/ssrolcmanager/popups/{pageNum}"},method = {RequestMethod.GET,RequestMethod.HEAD})
	@ResponseBody
	public ResponseEntity<Map<String,Object>> popupListJson(@PathVariable int pageNum){
		logger.debug("pageNum:"+pageNum);
		
		int popupCnt = popupService.getPopupCnt();
		if(popupCnt == 0){
			throw new PopupNotFoundException();
		}else{
			int rowBlockSize = 10;
			int pageBlockSize = 10;
			int totalRowCnt = popupCnt;
			
			PageUtil pageUtil = new	PageUtil(pageNum, totalRowCnt, rowBlockSize, pageBlockSize);
			
			Map<String,Object> map = new HashMap<>();
			map.put("pageInfo",pageUtil);
			map.put("popups",popupService.getPopups(pageUtil.getStartRow(),pageUtil.getEndRow()));
			return ResponseEntity.ok(map);
		}
	}

	//리스트 paging, 검색조건 추가
	@RequestMapping(value={"/ssrolcmanager/popups/{pageNum}/{searchField}/{searchValue}"},method = {RequestMethod.GET,RequestMethod.HEAD})
	@ResponseBody
	public ResponseEntity<Map<String,Object>> popupSearchListJson(@PathVariable int pageNum,@PathVariable String searchField,@PathVariable String searchValue){
		logger.debug("searchField:"+searchField+",searchValue:"+searchValue);
		
		int popupCnt = popupService.getPopupCnt();
		if(popupCnt == 0){
			throw new PopupNotFoundException();
		}else{
			int rowBlockSize = 10;
			int pageBlockSize = 10;
			int totalRowCnt = popupService.getPopupCnt(searchField,searchValue);
			
			PageUtil pageUtil = new	PageUtil(pageNum, totalRowCnt, rowBlockSize, pageBlockSize);
			
			Map<String,Object> map = new HashMap<>();
			map.put("pageInfo",pageUtil);
			map.put("popups",popupService.getPopups(pageUtil.getStartRow(),pageUtil.getEndRow(),searchField,searchValue));
			return ResponseEntity.ok(map);
		}
	}
		
	//쓰기
	@RequestMapping(value={"/ssrolcmanager/popups/new"},method = {RequestMethod.GET,RequestMethod.HEAD})
	public String popupWrite(Model model){
		model.addAttribute("title","러닝센터관리자 팝업관리");

		//해더에 css 추가
		List<String> headerCss = new ArrayList<>();
		headerCss.add("jquery-ui.1.11.4.min");

		model.addAttribute("headerCss",headerCss);

		//해더에 스크립트 추가
		List<String> headerScript = new ArrayList<>();
		headerScript.add("jquery-ui.1.11.4.min");
		headerScript.add("common");
		headerScript.add("ssrolcmanager/boards/admin_write");

		model.addAttribute("headerScript",headerScript);

		return "ssrolcmanager/popups/popupWrite";
	}


	
	
}
