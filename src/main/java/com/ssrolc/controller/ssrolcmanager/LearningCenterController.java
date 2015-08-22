package com.ssrolc.controller.ssrolcmanager;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssrolc.domain.franchise.Franchise;
import com.ssrolc.domain.learningcenter.LearningCenter;
import com.ssrolc.service.LearningCenterService;

@Controller
public class LearningCenterController {
	private static final Logger logger = LoggerFactory.getLogger(LearningCenterController.class);
	@Autowired
	private LearningCenterService learningCenterService;
	
	//우리동네 러닝센터 홈페이지 리스트 불러오기
	@RequestMapping(value={"/ssrolcmanager/centers"} , method =  { RequestMethod.GET, RequestMethod.HEAD })
	public String list(Model model, @RequestParam(value="area", defaultValue="") String area) {
			List<LearningCenter> learningCenters = learningCenterService.getCenters(area);
			model.addAttribute("learningCenters", learningCenters);
			return "ssrolcmanager/learningcenter/list";
		}
	
	//우리동네 러닝센터 보기
	@RequestMapping(value={"/ssrolcmanager/center/{deptId1}"}, method = { RequestMethod.GET, RequestMethod.HEAD })
	public String popDisclosure(Model model,@PathVariable String deptId1){
		//데이터읽어오기
		String centerName = learningCenterService.getCenter(deptId1);
		String homeUrl = learningCenterService.getHomeUrl(deptId1);
		model.addAttribute("deptId1",deptId1);
		model.addAttribute("centerName",centerName);
		model.addAttribute("homeUrl",homeUrl);
		return "ssrolcmanager/learningcenter/write";
	}
	//우리동네 러닝센터 등록
	@RequestMapping(value={"/ssrolcmanager/center"}, method = { RequestMethod.POST, RequestMethod.HEAD })
	public String insertUrl(LearningCenter learningCenter,@RequestParam(value="writeType", defaultValue="") String writeType ){
		//logger.debug("뭐야야야야야야야ㅑ{}",learningCenter);
		learningCenterService.writeLearningCenter(learningCenter,writeType);
		
		return "redirect:/ssrolcmanager/centers";
	}
	
	
}
