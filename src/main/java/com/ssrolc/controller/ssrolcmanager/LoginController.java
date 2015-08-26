package com.ssrolc.controller.ssrolcmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.expression.MapAccessor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;
import com.ssrolc.domain.board.Article;
import com.ssrolc.domain.common.User;
import com.ssrolc.domain.disclosure.Disclosure;
import com.ssrolc.domain.franchise.Franchise;
import com.ssrolc.service.AuthoritiesService;
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
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
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
		int newFranchiseCnt = franchiseService.getNewFranchiseWriteCnt();
		
		List<Article> articleList = boardService.getArticles(boardTable, 0,3);
		int newNoticeCnt = boardService.getNewNoticeArticleCnt();
		
		List<Disclosure> disclosureList = disclosureService.getSearchDisclosures(null,null,null,null,null,null,0,3);
		int newDisclosureCnt = disclosureService.getNewDisclosureCnt();		
		
		model.addAttribute("franchiseList",franchiseList);
		model.addAttribute("newFranchiseCnt",newFranchiseCnt);
		model.addAttribute("articleList",articleList);
		model.addAttribute("newNoticeCnt",newNoticeCnt);
		model.addAttribute("disclosureList",disclosureList);
		model.addAttribute("newDisclosureCnt",newDisclosureCnt);
		
		return "ssrolcmanager/main";
	}
	
	@RequestMapping(value={"/ssrolcmanager/edit"},method={RequestMethod.GET, RequestMethod.HEAD})
	public String passwordEditView(Model model){
		//해더에 스크립트 추가
		List<String> headerScript = new ArrayList<>();
		headerScript.add("ssrolcmanager/edit");
		
		model.addAttribute("headerScript",headerScript);
		
		return "ssrolcmanager/edit";
	}
	
	@RequestMapping(value={"/ssrolcmanager/edit"},method={RequestMethod.PUT})
	@ResponseBody
	public ResponseEntity<Map<String,Object>> updatePasswordJson(@CookieValue(value="SSROLC_ID") String userId
			,@RequestParam String oldPass,@RequestParam String newPass,@RequestParam String newPassConfirm){
		Map<String,Object> map = new HashMap<>();
		
		User user = authoritiesService.getUser(userId);
		
		if(!Strings.isNullOrEmpty(oldPass) && !Strings.isNullOrEmpty(newPass) && !Strings.isNullOrEmpty(newPassConfirm)){
			if(oldPass.equals(user.getUserPassword())){
				if(newPass.equals(newPassConfirm)){
					
					authoritiesService.setUserPassword(userId, oldPass, newPass);
					
					map.put("message","비밀번호가 변경되었습니다.");
				}
			}else{
				map.put("message","기존 비밀번호가 맞지 않습니다.");
			}
		}else{
			map.put("message","필수 입력 값들을 입력바랍니다.");
		}
		
		return ResponseEntity.ok(map);
	}
}
