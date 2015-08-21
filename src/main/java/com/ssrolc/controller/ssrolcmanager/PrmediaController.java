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

import com.ssrolc.domain.board.Article;
import com.ssrolc.domain.board.AttachFile;
import com.ssrolc.domain.board.Board;
import com.ssrolc.domain.prmedia.Prmedia;
import com.ssrolc.exception.ArticleNotFoundException;
import com.ssrolc.exception.BoardNotFoundException;
import com.ssrolc.exception.PrmediaNotFoundException;
import com.ssrolc.service.PrmediaService;
import com.ssrolc.utils.PageUtil;


@Controller
public class PrmediaController {
	private static final Logger logger = LoggerFactory.getLogger(PrmediaController.class);
	
	@Autowired
	private PrmediaService prmediaService;
	
	//리스트
	@RequestMapping(value={"/ssrolcmanager/prmedias"},method = {RequestMethod.GET,RequestMethod.HEAD})
	public String prmediaList(Model model){
		logger.debug("prmedia List");
		
		model.addAttribute("title","러닝센터관리자 홍보영상관리");
		
		//해더에 스크립트 추가
		List<String> headerScript = new ArrayList<>();
		headerScript.add("common");
		headerScript.add("ssrolcmanager/prmedia/admin_list");
		model.addAttribute("headerScript",headerScript);
		System.out.println("====================");
		return "/ssrolcmanager/prmedia/list";
	}
	
	//리스트 paging 추가
	@RequestMapping(value={"/ssrolcmanager/prmedias/{pageNum:[0-9]+}"},method = {RequestMethod.GET,RequestMethod.HEAD})
	@ResponseBody
	public ResponseEntity<Map<String,Object>> prmediaListJson(@PathVariable int pageNum){
		logger.debug("pageNum:"+pageNum);
		
		int prmediaCnt = prmediaService.getPrmediaCnt();
		if(prmediaCnt == 0){
			throw new PrmediaNotFoundException();
		}else{
			int rowBlockSize = 10;
			int pageBlockSize = 10;
			int totalRowCnt = prmediaCnt;
			//System.out.println("totalRowCnt========================================================"+totalRowCnt);
			PageUtil pageUtil = new	PageUtil(pageNum, totalRowCnt, rowBlockSize, pageBlockSize);
			
			
			Map<String,Object> map = new HashMap<>();
			map.put("pageInfo",pageUtil);
			map.put("prmedia",prmediaService.getPrmedias(pageUtil.getStartRow(),pageUtil.getEndRow()));
			return ResponseEntity.ok(map);
		}
	}

	//리스트 paging, 검색조건 추가
	@RequestMapping(value={"/ssrolcmanager/prmedias/{pageNum:[0-9]+}/{searchField}/{searchValue}"},method = {RequestMethod.GET,RequestMethod.HEAD})
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
		
	//쓰기
	@RequestMapping(value={"/ssrolcmanager/prmedias/new"},method = {RequestMethod.GET,RequestMethod.HEAD})
	public String prmediaWrite(Model model){
		model.addAttribute("title","러닝센터관리자 홍보영상관리");

		//해더에 css 추가
	/*	List<String> headerCss = new ArrayList<>();
		model.addAttribute("headerCss",headerCss);*/

		//해더에 스크립트 추가
		List<String> headerScript = new ArrayList<>();
		headerScript.add("ssrolcmanager/jdatepicker");
		headerScript.add("ssrolcmanager/prmedia/admin_write");

		model.addAttribute("headerScript",headerScript);

		return "ssrolcmanager/prmedia/write";
	}
	

	//글보기
	@RequestMapping(value={"/ssrolcmanager/prmedia/{aidx:[0-9]+}"},method = { RequestMethod.GET, RequestMethod.HEAD })	
	public String view(Model model,@PathVariable int aidx){
	
			Prmedia prmedia = prmediaService.getPrmedia(aidx);
			if(prmedia == null || prmedia.equals(null)){
				throw new PrmediaNotFoundException();
			}else{
				model.addAttribute("prmedia",prmedia);
				
				prmediaService.setPrmediaHitUp(aidx);
				
				return "ssrolcmanager/prmedia/view";
			}
	
	}
	
	
}

