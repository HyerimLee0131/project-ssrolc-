package com.ssrolc.controller.ssrolcmanager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssrolc.domain.board.Article;
import com.ssrolc.domain.disclosure.Disclosure;
import com.ssrolc.domain.franchise.Franchise;
import com.ssrolc.service.BoardService;
import com.ssrolc.service.DisclosureService;
import com.ssrolc.service.FranchiseService;


@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private FranchiseService franchiseService;
	
	@Autowired
	private DisclosureService disclosureService;
	
	@Value("${board.https.url}")
	private String loginCheckUrl;
	
	/**
	 * 로그인 페이지
	 * @param returl : 로그인성공되면 리턴될 url
	 * @return
	 */
	@RequestMapping(value={"/ssrolcmanager/login","/ssrolcmanager"})
	public String login(Model model,@RequestParam(value="returl",required=false) String returl
			,@RequestParam(value="error",required=false,defaultValue="") String error){
//      로그찍을때
//		logger.trace("trace");
//		logger.debug("login 들어옴");
//		logger.info("info");
//		logger.warn("warn login 들어옴");
//		logger.error("error");
		
		model.addAttribute("title", "로그인페이지");
		model.addAttribute("returl",returl);
		model.addAttribute("error",error);
		model.addAttribute("loginCheckUrl",loginCheckUrl);
	    return "ssrolcmanager/login";
	}
	
	@RequestMapping(value={"/ssrolcmanager/main"})
	public String main(Model model){
		model.addAttribute("title","러닝센터관리자 메인");
		
		final String boardTable = "notice";
		
		List<Franchise> franchiseList = franchiseService.getSearchFranchises(null,null,null,null,null,null,null,0,3);

		List<Article> articleList = boardService.getArticles(boardTable, 0,3);
		
		List<Disclosure> disclosureList = disclosureService.getSearchDisclosures(null,null,null,null,null,null,0,3);
		
		model.addAttribute("franchiseList",franchiseList);
		model.addAttribute("articleList",articleList);
		model.addAttribute("disclosureList",disclosureList);
		
		return "ssrolcmanager/main";
	}
}
