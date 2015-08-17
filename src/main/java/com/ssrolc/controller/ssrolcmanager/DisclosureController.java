package com.ssrolc.controller.ssrolcmanager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ssrolc.domain.disclosure.Disclosure;
import com.ssrolc.service.DisclosureService;

@Controller
public class DisclosureController {

	@Autowired
	private DisclosureService disclosureService;
	
	@RequestMapping(value="/ssrolcmanager/disclosure/{aidx}",method={RequestMethod.GET,RequestMethod.HEAD})
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
}
