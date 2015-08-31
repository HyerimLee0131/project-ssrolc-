package com.ssrolc.controller.ssrolcfront;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssrolc.exception.PrmediaNotFoundException;
import com.ssrolc.service.PrmediaService;
import com.ssrolc.utils.PageUtil;


@Controller
public class PrmediaFrontController {
	private static final Logger logger = LoggerFactory.getLogger(PrmediaFrontController.class);
	
	@Autowired
	private PrmediaService prmediaService;
	
	@Value("${uploadpath.prmedia}")
	private String prmediaUploadPath;
	
	//리스트
	@RequestMapping(value={"/ssrolcfront/prmedias"},method = {RequestMethod.GET,RequestMethod.HEAD})
	public String prmediaList(Model model){
		//logger.debug("prmedia List");
		
		model.addAttribute("title","러닝센터관리자 홍보영상관리");
		
		//해더에 스크립트 추가
		List<String> headerScript = new ArrayList<>();
		headerScript.add("ssrolcfront/prmedia/list");
		model.addAttribute("headerScript",headerScript);
		
		model.addAttribute("prmediaUrl",prmediaService.getPrmediaFirst());
		return "/ssrolcfront/prmedia/list";
	}
	
	//리스트 paging 추가
	@RequestMapping(value={"/ssrolcfront/prmedias/{pageNum:[0-9]+}"},method = {RequestMethod.GET,RequestMethod.HEAD})
	@ResponseBody
	public ResponseEntity<Map<String,Object>> prmediaListJson(@PathVariable int pageNum){
		
		int prmediaCnt = prmediaService.getPrmediaCnt();
		if(prmediaCnt == 0){
			throw new PrmediaNotFoundException();
		}else{
			int rowBlockSize = 10;
			int pageBlockSize = 10;
			int totalRowCnt = prmediaCnt;

			PageUtil pageUtil = new	PageUtil(pageNum, totalRowCnt, rowBlockSize, pageBlockSize);
			
			Map<String,Object> map = new HashMap<>();
			map.put("pageInfo",pageUtil);
			map.put("prmedia",prmediaService.getPrmedias(pageUtil.getStartRow(),pageUtil.getEndRow()));
			
			return ResponseEntity.ok(map);
		}
	}
	
	//리스트 paging, 검색조건 추가
	@RequestMapping(value={"/ssrolcfront/prmedias/{pageNum:[0-9]+}/{searchField}/{searchValue}"},method = {RequestMethod.GET,RequestMethod.HEAD})
	@ResponseBody
	public ResponseEntity<Map<String,Object>> prmediaSearchListJson(@PathVariable int pageNum,@PathVariable String searchField,@PathVariable String searchValue){
		logger.debug("searchField:"+searchField+",searchValue:"+searchValue);
		
		int prmediaCnt = prmediaService.getPrmediaCnt();
		if(prmediaCnt == 0){
			throw new PrmediaNotFoundException();
		}else{
			int rowBlockSize = 10;
			int pageBlockSize = 10;
			int totalRowCnt = prmediaService.getPrmediaCnt(searchField,searchValue);
			
			
			PageUtil pageUtil = new	PageUtil(pageNum, totalRowCnt, rowBlockSize, pageBlockSize);
			
			Map<String,Object> map = new HashMap<>();
			map.put("pageInfo",pageUtil);
			map.put("prmedia",prmediaService.getPrmedias(pageUtil.getStartRow(),pageUtil.getEndRow(),searchField,searchValue));
			return ResponseEntity.ok(map);
		}
	}
	
	//홍보영상 선택재생
	@RequestMapping(value={"/ssrolcfront/prmediasView/{aidx:[0-9]+}"},method = {RequestMethod.GET,RequestMethod.HEAD})
	public String prmediaListView(Model model,@PathVariable int aidx){
		//logger.debug("prmedia List");
		
		model.addAttribute("title","러닝센터관리자 홍보영상관리");
		
		//해더에 스크립트 추가
		List<String> headerScript = new ArrayList<>();
		headerScript.add("ssrolcfront/prmedia/list");
		model.addAttribute("headerScript",headerScript);
		
		model.addAttribute("prmediaUrl",prmediaService.getPrmediaView(aidx));
		return "/ssrolcfront/prmedia/list";
	}
	
}
	
