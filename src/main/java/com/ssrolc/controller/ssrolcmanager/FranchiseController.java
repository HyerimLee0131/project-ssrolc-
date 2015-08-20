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

import com.ssrolc.service.FranchiseService;
import com.ssrolc.utils.PageUtil;

@Controller
public class FranchiseController {

	@Autowired
	private FranchiseService franchiseService;
	
	
	@RequestMapping(value={"/ssrolcmanager/franchise/faqs"} , method =  { RequestMethod.GET, RequestMethod.HEAD })
	public String list(Model model) {
		
			List<String> cityList = franchiseService.getFranchiseCityList();
			//해더에 스크립트 추가
			List<String> headerScript = new ArrayList<>();
			headerScript.add("ssrolcmanager/franchise/list");
			
			model.addAttribute("headerScript",headerScript);
			model.addAttribute("cityList", cityList);
			return "ssrolcmanager/franchise/faqList";
		}
	
	@RequestMapping(value="/ssrolcmanager/franchise/{pageNum:[0-9]+}",method={RequestMethod.GET,RequestMethod.HEAD})
	@ResponseBody
	public ResponseEntity<Map<String, Object>> searchListJson(@PathVariable int pageNum
			,@RequestParam(value="jslcArea1")String jslcArea1
			,@RequestParam(value="jslcArea2") String jslcArea2
			,@RequestParam(value="jslcType") String jslcType
			,@RequestParam(value="startDate") String startDate
			,@RequestParam(value="endDate") String endDate
			,@RequestParam(value="jslcounseling") String jslcounseling
			,@RequestParam(value="memName") String memName ){
		int rowBlockSize = 10;
		int pageBlockSize = 10;
		int totalRowCnt = franchiseService.getSearchDisclosureCnt(jslcArea1,jslcArea2,jslcType,startDate,endDate,jslcounseling,memName);
		System.out.println("count : "+totalRowCnt);
		System.out.println(jslcArea1);
		System.out.println(jslcArea2);
		System.out.println(jslcType);
		System.out.println(startDate);
		System.out.println(endDate);
		System.out.println(jslcounseling);
		System.out.println(memName);
		
		PageUtil pageUtil = new PageUtil(pageNum, totalRowCnt, rowBlockSize, pageBlockSize);

		Map<String,Object> map = new HashMap<>();
		map.put("pageInfo",pageUtil);
		map.put("franchiseInfo", franchiseService.getSearchFranchises(jslcArea1,jslcArea2,jslcType,startDate,endDate,jslcounseling,memName,pageUtil.getStartRow(),pageUtil.getEndRow()));
		return ResponseEntity.ok(map);
	}
	
	@RequestMapping(value="/ssrolcmanager/franchise/deptArea",method={RequestMethod.GET,RequestMethod.HEAD})
	@ResponseBody
	public ResponseEntity<List<String>> getDeptArea(@RequestParam(value="jslcArea1")String jslcArea1){
		List<String> deptAreaList = franchiseService.getFranchiseDeptAreaList(jslcArea1);
		return ResponseEntity.ok(deptAreaList);
	}
	
	
	@RequestMapping(value="/ssrolcmanager/franchise/changeJoinState",method={RequestMethod.POST})
	@ResponseBody
	public String getFranchiseschangeJoinState(@RequestParam(value="jslcId")String jslcId,
											@RequestParam(value="jslcounseling")String jslcounseling){
		franchiseService.getchangeJoinState(jslcId,jslcounseling);
		return "update";
	}
	
	
}
