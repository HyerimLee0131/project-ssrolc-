package com.ssrolc.controller.ssrolcmanager;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssrolc.utils.Thumbnail;


@Controller
public class ThumnailController {
	private static final Logger logger = LoggerFactory.getLogger(ThumnailController.class);
	
	//썸네일테스트 - 변준영	
	@RequestMapping(value={"/ssrolcmanager/thumbnail"})
	public String thumbnail(Model model){
		model.addAttribute("title","러닝센터관리자 썸네일");
		Thumbnail th = new Thumbnail();
		th.thumbnailMake("C:\\imagetest", "skin_54.gif", 100);
		return "ssrolcmanager/index";
	}
}
