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

import com.ssrolc.domain.disclosure.Disclosure;
import com.ssrolc.service.DisclosureService;
import com.ssrolc.utils.PageUtil;

@Controller
public class DisclosureController {

	@Autowired
	private DisclosureService disclosureService;
	
	@RequestMapping(value="/ssrolcmanager/disclosure/{aidx:[0-9]+}",method={RequestMethod.GET,RequestMethod.HEAD})
	public String popDisclosure(Model model,@PathVariable int aidx){
		//데이터읽어오기
		Disclosure disclosure = disclosureService.getDisclosure(aidx);
		//해더에 스크립트 추가
		List<String> headerScript = new ArrayList<>();
		headerScript.add("disclosure");
		
		model.addAttribute("headerScript",headerScript);
		model.addAttribute("disclosure",disclosure);
		System.out.println(disclosure);
		return "ssrolcmanager/disclosures/popDisclosure";
	}
	
	
	@RequestMapping(value={"/ssrolcmanager/disclosures"} , method =  { RequestMethod.GET, RequestMethod.HEAD })
	public String list(Model model) {
		
			List<String> cityList = disclosureService.getDisclosureCityList();
			//해더에 스크립트 추가
			List<String> headerScript = new ArrayList<>();
			headerScript.add("ssrolcmanager/disclosure/list");
			
			model.addAttribute("headerScript",headerScript);
			model.addAttribute("cityList", cityList);
			return "ssrolcmanager/disclosures/disclosureList";
		}
	
	@RequestMapping(value="/ssrolcmanager/disclosures/{pageNum:[0-9]+}",method={RequestMethod.GET,RequestMethod.HEAD})
	@ResponseBody
	public ResponseEntity<Map<String, Object>> searchListJson(@PathVariable int pageNum
			,@RequestParam(value="deptArea1")String deptArea1
			,@RequestParam(value="deptArea2") String deptArea2
			,@RequestParam(value="deptType") String deptType
			,@RequestParam(value="startDate") String startDate
			,@RequestParam(value="endDate") String endDate
			,@RequestParam(value="memName") String memName ){
		int rowBlockSize = 10;
		int pageBlockSize = 10;
		int totalRowCnt = disclosureService.getSearchDisclosureCnt(deptArea1,deptArea2,deptType,startDate,endDate,memName);
		System.out.println("count : "+totalRowCnt);
		System.out.println(deptArea1);
		System.out.println(deptArea2);
		System.out.println(deptType);
		System.out.println(startDate);
		System.out.println(endDate);
		System.out.println(memName);
		
		PageUtil pageUtil = new PageUtil(pageNum, totalRowCnt, rowBlockSize, pageBlockSize);

		Map<String,Object> map = new HashMap<>();
		map.put("pageInfo",pageUtil);
		map.put("disclosureInfo", disclosureService.getSearchDisclosures(deptArea1,deptArea2,deptType,startDate,endDate,memName,pageUtil.getStartRow(),pageUtil.getEndRow()));
		return ResponseEntity.ok(map);
	}
	
	@RequestMapping(value="/ssrolcmanager/disclosures/deptArea",method={RequestMethod.GET,RequestMethod.HEAD})
	@ResponseBody
	public ResponseEntity<List<String>> getDeptArea(@RequestParam(value="deptArea1")String deptArea1){
		List<String> deptAreaList = disclosureService.getDisclosureDeptAreaList(deptArea1);
		return ResponseEntity.ok(deptAreaList);
	}
	
}
