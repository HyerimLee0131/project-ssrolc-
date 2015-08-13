package com.ssrolc.controller.ssrolcmanager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@RequestMapping(value={"/ssrolcmanager/boards/{boardTable}"})
	public String list(Model model){
		
		return "";
	}
}
