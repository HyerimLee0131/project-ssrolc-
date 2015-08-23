package com.ssrolc.controller.ssrolcmanager;


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
import com.ssrolc.domain.board.Article;
import com.ssrolc.domain.board.AttachFile;
import com.ssrolc.domain.board.Board;
import com.ssrolc.domain.prmedia.Prmedia;
import com.ssrolc.domain.prmedia.ThumbUpdateInfo;
import com.ssrolc.exception.ArticleNotAddException;
import com.ssrolc.exception.ArticleNotFoundException;
import com.ssrolc.exception.BoardNotFoundException;
import com.ssrolc.exception.PrmediaNotFoundException;
import com.ssrolc.service.PrmediaService;
import com.ssrolc.utils.FileUploadUtil;
import com.ssrolc.utils.PageUtil;
import com.ssrolc.utils.ThumbFileUploadUtil;


@Controller
public class PrmediaController {
	private static final Logger logger = LoggerFactory.getLogger(PrmediaController.class);
	
	@Autowired
	private PrmediaService prmediaService;
	
	@Value("${uploadpath.prmedia}")
	private String prmediaUploadPath;
	
	//리스트
	@RequestMapping(value={"/ssrolcmanager/prmedias"},method = {RequestMethod.GET,RequestMethod.HEAD})
	public String prmediaList(Model model){
		logger.debug("prmedia List");
		
		model.addAttribute("title","러닝센터관리자 홍보영상관리");
		
		//해더에 스크립트 추가
		List<String> headerScript = new ArrayList<>();
		headerScript.add("ssrolcmanager/prmedia/admin_list");
		model.addAttribute("headerScript",headerScript);
	
		return "/ssrolcmanager/prmedia/list";
	}
	
	//리스트 paging 추가
	@RequestMapping(value={"/ssrolcmanager/prmedias/{pageNum:[0-9]+}"},method = {RequestMethod.GET,RequestMethod.HEAD})
	@ResponseBody
	public ResponseEntity<Map<String,Object>> prmediaListJson(@PathVariable int pageNum){
		logger.debug("pageNum:"+pageNum);
		
		int prmediaCnt = prmediaService.getPrmediaCnt();
		if(prmediaCnt == 0){
			throw new PrmediaNotFoundException();
		}else{
			int rowBlockSize = 10;
			int pageBlockSize = 10;
			int totalRowCnt = prmediaCnt;
			//System.out.println("totalRowCnt========================================================"+totalRowCnt);
			PageUtil pageUtil = new	PageUtil(pageNum, totalRowCnt, rowBlockSize, pageBlockSize);
			
			
			Map<String,Object> map = new HashMap<>();
			map.put("pageInfo",pageUtil);
			map.put("prmedia",prmediaService.getPrmedias(pageUtil.getStartRow(),pageUtil.getEndRow()));
			return ResponseEntity.ok(map);
		}
	}

	//리스트 paging, 검색조건 추가
	@RequestMapping(value={"/ssrolcmanager/prmedias/{pageNum:[0-9]+}/{searchField}/{searchValue}"},method = {RequestMethod.GET,RequestMethod.HEAD})
	@ResponseBody
	public ResponseEntity<Map<String,Object>> prmediaSearchListJson(@PathVariable int pageNum,@PathVariable String searchField,@PathVariable String searchValue){
		logger.debug("searchField:"+searchField+",searchValue:"+searchValue);
		
		int prmediaCnt = prmediaService.getPrmediaCnt();
		if(prmediaCnt == 0){
			throw new PrmediaNotFoundException();
		}else{
			int rowBlockSize = 10;
			int pageBlockSize = 10;
			int totalRowCnt = prmediaService.getPrmediaCnt(searchField,searchValue);
			
			
			PageUtil pageUtil = new	PageUtil(pageNum, totalRowCnt, rowBlockSize, pageBlockSize);
			
			Map<String,Object> map = new HashMap<>();
			map.put("pageInfo",pageUtil);
			map.put("prmedia",prmediaService.getPrmedias(pageUtil.getStartRow(),pageUtil.getEndRow(),searchField,searchValue));
			return ResponseEntity.ok(map);
		}
	}
		
	//쓰기 페이지
	@RequestMapping(value={"/ssrolcmanager/prmedias/new"},method = {RequestMethod.GET,RequestMethod.HEAD})
	public String prmediaWrite(Model model){
		model.addAttribute("title","러닝센터관리자 홍보영상관리");

		//해더에 css 추가
	/*	List<String> headerCss = new ArrayList<>();
		model.addAttribute("headerCss",headerCss);*/

		//해더에 스크립트 추가
		List<String> headerScript = new ArrayList<>();
		headerScript.add("ssrolcmanager/prmedia/admin_write");

		model.addAttribute("headerScript",headerScript);
		
		
		Map<String,Object> map = new HashMap<>();
		map.put("aidx","");
		map.put("aidx","");
		map.put("aidx","");
		map.put("aidx","");
		map.put("aidx","");
		
		return "ssrolcmanager/prmedia/write";
	}
	

	//상세 페이지
	@RequestMapping(value={"/ssrolcmanager/prmedia/{aidx:[0-9]+}"},method = { RequestMethod.GET, RequestMethod.HEAD })	
	public String view(Model model,@PathVariable int aidx){
	
			Prmedia prmedia = prmediaService.getPrmedia(aidx);
			if(prmedia == null || prmedia.equals(null)){
				throw new PrmediaNotFoundException();
			}else{
				//해더에 스크립트 추가
				List<String> headerScript = new ArrayList<>();
				headerScript.add("ssrolcmanager/prmedia/admin_view");
				model.addAttribute("headerScript",headerScript);
				
				model.addAttribute("prmedia",prmedia);
							
				prmediaService.setPrmediaHitUp(aidx);
				
				return "ssrolcmanager/prmedia/view";
			}
	}
	
	//글저장 처리
	@RequestMapping(value="/ssrolcmanager/prmedias/write",method=RequestMethod.POST)
	public String addPrmedia(Model model,@CookieValue(value="SSROLC_ID") String regId
							,@RequestParam(value="prTitle") String prTitle
							,@RequestParam(value="makeTime") String makeTime
							,@RequestParam(value="mediaLinkUrl") String mediaLinkUrl
							,@RequestParam(value="mediaLocation") String mediaLocation
							,MultipartHttpServletRequest mhRequest) {
		
		
			if(Strings.isNullOrEmpty(prTitle) || Strings.isNullOrEmpty(mediaLinkUrl)){
				throw new PrmediaNotFoundException();
			}
			
			Timestamp nowDate = new Timestamp(new Date().getTime());
			
		
			Prmedia prmedia = new Prmedia(prTitle, makeTime,"","","",0,mediaLinkUrl,mediaLocation,null,"",nowDate, regId, mhRequest.getRemoteAddr());
			
			prmediaService.addPrmedia(prmedia);
			
			int lastAidx = prmedia.getAidx();
			
				
			String uploadPath = prmediaUploadPath;
			
			ThumbFileUploadUtil thumbFileUploadUtil = new ThumbFileUploadUtil(mhRequest, uploadPath, new ArrayList<ThumbUpdateInfo>(), 120);
			
			List<ThumbUpdateInfo> uploadedThumbFileList = thumbFileUploadUtil.doFileUpload();
			
			for (ThumbUpdateInfo thumbUpdateInfo : uploadedThumbFileList) {
				logger.debug(thumbUpdateInfo.getFileName()+","+thumbUpdateInfo.getThumnailName()+":"+thumbUpdateInfo.getThumnailSize());
				
				prmediaService.setThumbUpdatePrmedia(lastAidx,thumbUpdateInfo.getFileName(),thumbUpdateInfo.getThumnailName(),thumbUpdateInfo.getThumnailSize());
				
			}
			
			
			return "redirect:/ssrolcmanager/prmedias";
		}
	
	
		//글수정 페이지
		@RequestMapping(value={"/ssrolcmanager/prmedias/edit/{aidx:[0-9]+}"},method = { RequestMethod.GET, RequestMethod.HEAD })	
		public String edit(Model model,@PathVariable int aidx){
		
				Prmedia prmedia = prmediaService.getPrmedia(aidx);
				if(prmedia == null || prmedia.equals(null)){
					throw new PrmediaNotFoundException();
				}else{
					model.addAttribute("prmedia",prmedia);
					
					List<String> headerScript = new ArrayList<>();
					headerScript.add("ssrolcmanager/prmedia/admin_write");

					model.addAttribute("headerScript",headerScript);
					
					Map<String,Object> map = new HashMap<>();
					map.put("aidx",aidx);
					
					return "ssrolcmanager/prmedia/edit";
				}
		
		}
		
		//글수정 처리
		@RequestMapping(value="/ssrolcmanager/prmedias/edit/{aidx:[0-9]+}",method=RequestMethod.POST)
		public String editPrmedia(Model model,@CookieValue(value="SSROLC_ID") String regId
								,@PathVariable int aidx
								,@RequestParam(value="prTitle") String prTitle
								,@RequestParam(value="makeTime") String makeTime
								/*
								,@RequestParam(value="thumnailName") String thumnailName
								,@RequestParam(value="thumnailRealName") String thumnailRealName
								,@RequestParam(value="thumnailSize") int thumnailSize*/
								
								,@RequestParam(value="mediaLinkUrl") String mediaLinkUrl
								,@RequestParam(value="mediaLocation") String mediaLocation
								,MultipartHttpServletRequest mhRequest) {
			
			
				if(Strings.isNullOrEmpty(prTitle) || Strings.isNullOrEmpty(mediaLinkUrl)){
					throw new PrmediaNotFoundException();
				}
				
				Timestamp nowDate = new Timestamp(new Date().getTime());
				
				Prmedia prmedia = new Prmedia(aidx,prTitle, makeTime,"","",0,0,mediaLinkUrl,mediaLocation,nowDate,regId,nowDate, regId, mhRequest.getRemoteAddr());
				
				prmediaService.setPrmedia(prmedia);
				
				
				String uploadPath = prmediaUploadPath;
				
				ThumbFileUploadUtil thumbFileUploadUtil = new ThumbFileUploadUtil(mhRequest, uploadPath, new ArrayList<ThumbUpdateInfo>(), 120);
				
				List<ThumbUpdateInfo> uploadedThumbFileList = thumbFileUploadUtil.doFileUpload();
				
				for (ThumbUpdateInfo thumbUpdateInfo : uploadedThumbFileList) {
					logger.debug(thumbUpdateInfo.getFileName()+","+thumbUpdateInfo.getThumnailName()+":"+thumbUpdateInfo.getThumnailSize());
					
					prmediaService.setThumbUpdatePrmedia(aidx,thumbUpdateInfo.getFileName(),thumbUpdateInfo.getThumnailName(),thumbUpdateInfo.getThumnailSize());
					
				}
				
				return "redirect:/ssrolcmanager/prmedias";
			}
		
		//파일정보 
		@RequestMapping(value="/ssrolcmanager/prmedias/thumbview/{thumnailRealName}/{thumnailSize}",method={RequestMethod.GET} ,produces={MediaType.IMAGE_GIF_VALUE,MediaType.IMAGE_JPEG_VALUE,MediaType.IMAGE_PNG_VALUE})
		@ResponseBody
		public ResponseEntity<InputStreamResource> thumbStream(HttpServletResponse res ,@PathVariable String thumnailRealName,@PathVariable int thumnailSize) throws FileNotFoundException{
			
			String imageFilePath = prmediaUploadPath+File.separator+"thumb"+File.separator+thumnailRealName;
			System.out.println();
			File imageFile = new File(imageFilePath);
			
			FileInputStream fis = new FileInputStream(imageFile);
			
			return ResponseEntity.ok()
					.contentLength(thumnailSize)
					.body(new InputStreamResource(fis));
		}
		
		// 글 삭제
		@RequestMapping(value="/ssrolcmanager/prmedias/delete/{aidx:[0-9]+}",method=RequestMethod.DELETE)
		@ResponseStatus(value=HttpStatus.NO_CONTENT)
		public void delete(@PathVariable int aidx){
			String uploadPath = prmediaUploadPath;
			
			Prmedia prmedia = prmediaService.getPrmedia(aidx);
			String filePath = uploadPath+File.separator+prmedia.getThumnailRealName();
			File file = new File(filePath);
			if(file.exists()){
				file.delete();
			}
			
			prmediaService.removePrmedia(aidx);
		}
		
	}
	
