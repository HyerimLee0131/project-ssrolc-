package com.ssrolc.controller.ssrolcmanager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Disclosure {

	@RequestMapping(value="/ssrolcmanager/disclosure/{aidx}",method={RequestMethod.GET,RequestMethod.HEAD})
	public String popDisclosure(Model model,@PathVariable int aidx){
		//데이터읽어오기
		
		//해더에 스크립트 추가
		List<String> headerScript = new ArrayList<>();
		headerScript.add("disclosure");
		
		model.addAttribute("headerScript",headerScript);
		
		return "ssrolcmanager/disclosure/popDisclosure";
	}
}
