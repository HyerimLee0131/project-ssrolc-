package com.ssrolc.controller.ssrolcmanager;


import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.common.base.Strings;
import com.ssrolc.domain.board.AttachFile;
import com.ssrolc.domain.popup.Popup;
import com.ssrolc.exception.PopupNotAddException;
import com.ssrolc.exception.PopupNotFoundException;
import com.ssrolc.service.PopupService;
import com.ssrolc.utils.FileUploadUtil;
import com.ssrolc.utils.PageUtil;


@Controller
public class PopupController {
	private static final Logger logger = LoggerFactory.getLogger(PopupController.class);
	
	@Autowired
	private PopupService popupService;

	@Value("${uploadpath.boards}")
	private String popupUploadPath;
	
	//리스트
	@RequestMapping(value={"/ssrolcmanager/popups"},method = {RequestMethod.GET,RequestMethod.HEAD})
	public String popupList(Model model){
		logger.debug("popup List");

		model.addAttribute("title","러닝센터관리자 팝업관리");

		//해더에 스크립트 추가
		List<String> headerScript = new ArrayList<>();
		headerScript.add("ssrolcmanager/popups/list");
		model.addAttribute("headerScript",headerScript);

		return "ssrolcmanager/popups/popupList";
	}
	
	//리스트 paging 추가
	@RequestMapping(value={"/ssrolcmanager/popups/{pageNum:[0-9]+}"},method = {RequestMethod.GET,RequestMethod.HEAD})
	@ResponseBody
	public ResponseEntity<Map<String,Object>> popupListJson(@PathVariable int pageNum){
		logger.debug("pageNum:"+pageNum);

		int popupCnt = popupService.getPopupCnt();
		if(popupCnt == 0){
			throw new PopupNotFoundException();
		}else{
			int rowBlockSize = 10;
			int pageBlockSize = 10;
			int totalRowCnt = popupCnt;

			PageUtil pageUtil = new	PageUtil(pageNum, totalRowCnt, rowBlockSize, pageBlockSize);

			Map<String,Object> map = new HashMap<>();
			map.put("pageInfo",pageUtil);
			map.put("popups",popupService.getPopups(pageUtil.getStartRow(),pageUtil.getEndRow()));
			return ResponseEntity.ok(map);
		}
	}

	//리스트 paging, 검색조건 추가
	@RequestMapping(value={"/ssrolcmanager/popups/{pageNum:[0-9]+}/{searchField}/{searchValue}"},method = {RequestMethod.GET,RequestMethod.HEAD})
	@ResponseBody
	public ResponseEntity<Map<String,Object>> popupSearchListJson(@PathVariable int pageNum,@PathVariable String searchField,@PathVariable String searchValue){
		logger.debug("searchField:"+searchField+",searchValue:"+searchValue);

		int popupCnt = popupService.getPopupCnt();
		if(popupCnt == 0){
			throw new PopupNotFoundException();
		}else{
			int rowBlockSize = 10;
			int pageBlockSize = 10;
			int totalRowCnt = popupService.getPopupCnt(searchField,searchValue);

			PageUtil pageUtil = new	PageUtil(pageNum, totalRowCnt, rowBlockSize, pageBlockSize);

			Map<String,Object> map = new HashMap<>();
			map.put("pageInfo",pageUtil);
			map.put("popups",popupService.getPopups(pageUtil.getStartRow(),pageUtil.getEndRow(),searchField,searchValue));
			return ResponseEntity.ok(map);
		}
	}

	//조회
	@RequestMapping(value={"/ssrolcmanager/popup/{aidx:[0-9]+}"},method={RequestMethod.GET,RequestMethod.HEAD})
	public String popupView(Model model,@PathVariable int aidx){
		logger.debug("popup View");

		model.addAttribute("title","러닝센터관리자 팝업관리");

		//데이터읽어오기
		Popup popup = popupService.getPopup(aidx);

		//해더에 스크립트 추가
		List<String> headerScript = new ArrayList<>();
		headerScript.add("ssrolcmanager/popups/write");

		model.addAttribute("headerScript",headerScript);
		model.addAttribute("popup",popup);

		return "ssrolcmanager/popups/popupWrite";
	}

	//삭제
	@RequestMapping(value="/ssrolcmanager/popups/delete", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Map<String,Object>> popupDeleteJson(@RequestParam(value="aidxs[]", required = false) String[] aidxs){
		logger.debug("popup Delete : "+aidxs);

		for (String aidx: aidxs) {
			popupService.setPopupsDel(aidx);
		}

		Map<String,Object> map = new HashMap<>();
		map.put("rs", "ok");

		return ResponseEntity.ok(map);
	}

	//쓰기
	@RequestMapping(value={"/ssrolcmanager/popups/new"},method = {RequestMethod.GET,RequestMethod.HEAD})
	public String popupWrite(Model model){
		model.addAttribute("title","러닝센터관리자 팝업관리");

		//해더에 css 추가
		List<String> headerCss = new ArrayList<>();
		headerCss.add("jquery-ui.1.11.4.min");

		model.addAttribute("headerCss",headerCss);

		//해더에 스크립트 추가
		List<String> headerScript = new ArrayList<>();
		headerScript.add("jquery-ui.1.11.4.min");
		headerScript.add("ssrolcmanager/popups/write");

		model.addAttribute("headerScript",headerScript);

		return "ssrolcmanager/popups/popupWrite";
	}

	//등록
	@RequestMapping(value="/ssrolcmanager/popups",method=RequestMethod.POST)
	public String addPopup(Model model,@CookieValue(value="SSROLC_ID") String regId
							,@RequestParam(value="pName") String pName
							,@RequestParam(value="pState") String pState
							,@RequestParam(value="startDate") String startDate
							,@RequestParam(value="endDate") String endDate
							,@RequestParam(value="pSize_width") String pSize_width
							,@RequestParam(value="pSize_height") String pSize_height
							,@RequestParam(value="location_top") String location_top
							,@RequestParam(value="location_left") String location_left
							,@RequestParam(value="pPopup_id") String pPopup_id){
							//,MultipartHttpServletRequest mhRequest){
		logger.debug("====================================");
		logger.debug("popup Add");
		
		if(Strings.isNullOrEmpty(pName) || Strings.isNullOrEmpty(pPopup_id)){
			throw new PopupNotAddException();
		}

//		Timestamp nowDate = new Timestamp(new Date().getTime());

/*
		startDate = startDate + " 00:00:00";
		java.sql.Timestamp pStartDate = java.sql.Timestamp.valueOf(startDate);
		endDate = endDate + " 00:00:00";
		java.sql.Timestamp pEndDate = java.sql.Timestamp.valueOf(endDate);
*/		
		logger.debug("pStartDate : "+startDate);
		
		Popup popup = new Popup(pPopup_id, pName, pSize_width, pSize_height, location_top, location_left
				, "", startDate, endDate, pState, null, regId, "");
//mhRequest.getRemoteAddr()
		popupService.addPopup(popup);
/*		
		int lastPopupNo = popup.getAidx();

		String uploadPath = popupUploadPath+File.separator+"popups";
		
		FileUploadUtil fileUploadUtil = new FileUploadUtil(mhRequest, "all"
				, uploadPath,new ArrayList<AttachFile>(),"popups",lastPopupNo, false, "M"
				, regId, mhRequest.getRemoteAddr(),nowDate);
		
		List<AttachFile> uploadedAttachFileList = fileUploadUtil.doFileUpload();
		
		int imageCnt = 0;
		int fileCnt = 0;
		
		for (AttachFile attachFile : uploadedAttachFileList) {
			boardService.addAttachFile(attachFile);
			if("jpg".equals(attachFile.getFileType()) || "png".equals(attachFile.getFileType()) 
					|| "gif".equals(attachFile.getFileType())){
				imageCnt++;
			}else{
				fileCnt++;
			}
		}
		
		boardService.setArticleFileCnt(lastArticleNo, fileCnt, imageCnt);
*/
		return "redirect:/ssrolcmanager/popups";
	}
}
