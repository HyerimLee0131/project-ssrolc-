package com.ssrolc.controller.ssrolcmanager;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;
import com.ssrolc.domain.board.Board;
import com.ssrolc.domain.board.BoardCategory;
import com.ssrolc.domain.disclosure.Disclosure;
import com.ssrolc.exception.BoardCategoryNotFoundException;
import com.ssrolc.exception.BoardNotFoundException;
import com.ssrolc.service.DisclosureService;
import com.ssrolc.utils.PageUtil;

@Controller
public class DisclosureController {

	@Autowired
	private DisclosureService disclosureService;
	
	@RequestMapping(value="/ssrolcmanager/disclosure/aidx={aidx}",method={RequestMethod.GET,RequestMethod.HEAD})
	public String popDisclosure(Model model,@PathVariable int aidx){
		//데이터읽어오기
		Disclosure disclosure = disclosureService.getDisclosureByIdx(aidx);
		//해더에 스크립트 추가
		List<String> headerScript = new ArrayList<>();
		headerScript.add("disclosure");
		
		model.addAttribute("headerScript",headerScript);
		model.addAttribute("disclosure",disclosure);
		System.out.println(disclosure);
		return "ssrolcmanager/disclosure/popDisclosure";
	}
	
	
	@RequestMapping(value={"/ssrolcmanager/disclosure/disclosureList"} , method =  { RequestMethod.GET, RequestMethod.HEAD })
	public String list(Model model) {
		
			
			//해더에 스크립트 추가
			List<String> headerScript = new ArrayList<>();
			headerScript.add("ssrolcmanager/disclosure/list");
			
			model.addAttribute("headerScript",headerScript);
			
			return "ssrolcmanager/disclosure/disclosureList";
		}
	
	@RequestMapping(value="/ssrolcmanager/disclosure/disclosureList/{pageNum}",method={RequestMethod.GET,RequestMethod.HEAD})
	@ResponseBody
	public ResponseEntity<Map<String,Object>> listJson(@PathVariable int pageNum){
		int rowBlockSize = 10;
		int pageBlockSize = 10;
		int totalRowCnt = disclosureService.getDisclosureCnt();
		
		PageUtil pageUtil = new PageUtil(pageNum, totalRowCnt, rowBlockSize, pageBlockSize);

		Map<String,Object> map = new HashMap<>();
		map.put("pageInfo",pageUtil);
		map.put("disclosureInfo",disclosureService.getDisclosureInfo(pageUtil.getStartRow(),pageUtil.getEndRow()));
		return ResponseEntity.ok(map);

	}
	@RequestMapping(value="/ssrolcmanager/disclosure/disclosureList/{pageNum}",method={RequestMethod.GET,RequestMethod.HEAD})
	@ResponseBody
	public ResponseEntity<Map<String, Object>> searchListJson(@PathVariable int pageNum
			,@RequestParam(value="hopeArea01")String hopeArea01
			,@RequestParam(value="hopeArea02") String hopeArea02
			,@RequestParam(value="deptType1") String deptType1
			,@RequestParam(value="deptType2") String deptType2
			,@RequestParam(value="deptType3") String deptType3
			,@RequestParam(value="startDate") String startDate
			,@RequestParam(value="endDate") String endDate
			,@RequestParam(value="pMemName") String pMemName ){
		int rowBlockSize = 10;
		int pageBlockSize = 10;
		int totalRowCnt = disclosureService.getSearchDisclosureCnt();
		
		PageUtil pageUtil = new PageUtil(pageNum, totalRowCnt, rowBlockSize, pageBlockSize);

		Map<String,Object> map = new HashMap<>();
		map.put("pageInfo",pageUtil);
		map.put("disclosureInfo", disclosureService.getDisclosureInfo(pageUtil.getStartRow(),pageUtil.getEndRow()));
		return ResponseEntity.ok(map);
	}
	
}
