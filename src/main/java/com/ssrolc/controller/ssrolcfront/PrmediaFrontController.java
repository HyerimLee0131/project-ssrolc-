package com.ssrolc.controller.ssrolcfront;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.common.base.Strings;
import com.ssrolc.domain.common.UploadFileInfo;
import com.ssrolc.domain.prmedia.Prmedia;
import com.ssrolc.exception.PrmediaNotFoundException;
import com.ssrolc.service.PrmediaService;
import com.ssrolc.utils.FileUploadUtil;
import com.ssrolc.utils.PageUtil;


@Controller
public class PrmediaFrontController {
	private static final Logger logger = LoggerFactory.getLogger(PrmediaFrontController.class);
	
	@Autowired
	private PrmediaService prmediaService;
	
	@Value("${uploadpath.prmedia}")
	private String prmediaUploadPath;
	
	//리스트
	@RequestMapping(value={"/ssrolcfront/prmedias"},method = {RequestMethod.GET,RequestMethod.HEAD})
	public String prmediaList(Model model){
		logger.debug("prmedia List");
		
		model.addAttribute("title","러닝센터관리자 홍보영상관리");
		
		//해더에 스크립트 추가
		List<String> headerScript = new ArrayList<>();
		headerScript.add("ssrolcmanager/prmedia/admin_list");
		model.addAttribute("headerScript",headerScript);
	
		return "/ssrolcfront/prmedia/list";
		}
	
	
		
	}
	
