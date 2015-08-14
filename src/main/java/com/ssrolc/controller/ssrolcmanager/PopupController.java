package com.ssrolc.controller.ssrolcmanager;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

public class PopupController {
	/*썸네일테스트 - 변준영	
	@RequestMapping(value={"/ssrolcmanager/thumbnail"})
	public String thumbnail(Model model){
		model.addAttribute("title","러닝센터관리자 썸네일");
		Thumbnail th = new Thumbnail();
		th.thumbnailMake("C:\\imagetest", "skin_54.gif", 100);
		return "ssrolcmanager/index";
	}
*/
	
	@RequestMapping(value={"/ssrolcmanager/popup"})
	public String popup(Model model){
		model.addAttribute("title","러닝센터관리자 팝업관리");
		return "ssrolcmanager/popupWrite";
	}
}
