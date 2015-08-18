package com.ssrolc.controller.ssrolcmanager;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class PrmediaController {
	private static final Logger logger = LoggerFactory.getLogger(PrmediaController.class);
	
	/*썸네일테스트 - 변준영	
	@RequestMapping(value={"/ssrolcmanager/thumbnail"})
	public String thumbnail(Model model){
		model.addAttribute("title","러닝센터관리자 썸네일");
		Thumbnail th = new Thumbnail();
		th.thumbnailMake("C:\\imagetest", "skin_54.gif", 100);
		return "ssrolcmanager/index";
	}
*/
	
	@RequestMapping(value={"/ssrolcmanager/prmedia/new"},method = {RequestMethod.GET,RequestMethod.HEAD})
	public String prmediaWrite(Model model){
		model.addAttribute("title","러닝센터관리자 팝업관리");

		//해더에 css 추가
		List<String> headerCss = new ArrayList<>();
		headerCss.add("jquery-ui.1.11.4.min");

		model.addAttribute("headerCss",headerCss);

		//해더에 스크립트 추가
		List<String> headerScript = new ArrayList<>();
		headerScript.add("jquery-ui.1.11.4.min");
		headerScript.add("common");
		headerScript.add("admin_popups");

		model.addAttribute("headerScript",headerScript);

		return "ssrolcmanager/prmedia/write";
	}
	
	@RequestMapping(value={"/ssrolcmanager/prmedia"},method = {RequestMethod.GET,RequestMethod.HEAD})
	public String prmediaList(Model model){
		model.addAttribute("title","러닝센터관리자 팝업관리");
		
		//해더에 스크립트 추가
		/*List<String> headerScript = new ArrayList<>();
		headerScript.add("common");

		model.addAttribute("headerScript",headerScript);
		*/
		return "ssrolcmanager/prmedia/list";
	}
	

}
