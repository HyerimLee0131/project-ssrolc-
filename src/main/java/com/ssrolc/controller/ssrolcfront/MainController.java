package com.ssrolc.controller.ssrolcfront;

import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssrolc.domain.myungsimbogam.Myungsimbogam;
import com.ssrolc.service.MainService;



@Controller
public class MainController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	private MainService mainService;
	
	
	@RequestMapping(value={"/"},method ={RequestMethod.GET,RequestMethod.HEAD})
	public String main(Model model){
		Calendar cal = Calendar.getInstance();
		int weekOfYear = cal.get(Calendar.WEEK_OF_YEAR);
		logger.debug("######################몇주차인가 {}",weekOfYear);
		//명심보감 데이터 가져오기
		Myungsimbogam myungsimbogam = mainService.getData(weekOfYear);
		
		model.addAttribute("title","러닝센터사용자 메인");
		model.addAttribute("myungsimbogam",myungsimbogam);
	
		
		return "ssrolcfront/index";
	}
	
}


