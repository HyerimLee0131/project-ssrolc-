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
import com.ssrolc.domain.common.UploadFileInfo;
import com.ssrolc.domain.popup.Popup;
import com.ssrolc.exception.PopupNotAddException;
import com.ssrolc.exception.PopupNotFoundException;
import com.ssrolc.service.BoardService;
import com.ssrolc.service.PopupService;
import com.ssrolc.utils.FileUploadUtil;
import com.ssrolc.utils.PageUtil;


@Controller
public class PopupController {
	private static final Logger logger = LoggerFactory.getLogger(PopupController.class);
	
	@Autowired
	private PopupService popupService;
	@Autowired
	private BoardService boardService;

	@Value("${uploadpath.boards}")
	private String boardUploadPath;
	//리스트
	@RequestMapping(value={"/ssrolcmanager/popups"},method = {RequestMethod.GET,RequestMethod.HEAD})
	public String popupList(Model model){

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

		model.addAttribute("title","러닝센터관리자 팝업관리");

		//데이터읽어오기
		Popup popup = popupService.getPopup(aidx);

		//해더에 스크립트 추가
		List<String> headerScript = new ArrayList<>();
		headerScript.add("crosseditor30/js/namo_scripteditor");
		headerScript.add("ssrolcmanager/popups/write");

		model.addAttribute("headerScript",headerScript);
		model.addAttribute("popup",popup);

		return "ssrolcmanager/popups/popupWrite";
	}

	//삭제
	@RequestMapping(value="/ssrolcmanager/popups/delete", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Map<String,Object>> popupDeleteJson(@RequestParam(value="aidxs[]", required = false) String[] aidxs){

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

		//해더에 스크립트 추가
		List<String> headerScript = new ArrayList<>();
		headerScript.add("crosseditor30/js/namo_scripteditor");
		headerScript.add("ssrolcmanager/popups/write");

		model.addAttribute("headerScript",headerScript);

		return "ssrolcmanager/popups/popupWrite";
	}

	//등록
	@RequestMapping(value="/ssrolcmanager/popups",method=RequestMethod.POST)
	public String addPopup(Model model,@CookieValue(value="SSROLC_ID") String pRegId
							,@RequestParam(value="pAidx") int pAidx
							,@RequestParam(value="pName") String pName
							,@RequestParam(value="pState") String pState
							,@RequestParam(value="pContent") String pContent
							,@RequestParam(value="pStartDate") String pStartDate
							,@RequestParam(value="pEndDate") String pEndDate
							,@RequestParam(value="pSize_width") String pSize_width
							,@RequestParam(value="pSize_height") String pSize_height
							,@RequestParam(value="pLocation_top") String pLocation_top
							,@RequestParam(value="pLocation_left") String pLocation_left
							,@RequestParam(value="pPopup_id") String pPopup_id
							,@RequestParam(value="writeType", defaultValue="") String writeType){
		
		if(Strings.isNullOrEmpty(pName) || Strings.isNullOrEmpty(pPopup_id)){
			throw new PopupNotAddException();
		}

		Popup popup = new Popup(pAidx, pPopup_id, pName, pSize_width, pSize_height, pLocation_top, pLocation_left
				, pContent, "", pStartDate, pEndDate, pState, null, pRegId, null, pRegId, "");
		popupService.addPopup(popup, writeType);

		return "redirect:/ssrolcmanager/popups";
	}
	
	
	@RequestMapping(value="/ssrolcmanager/popups/imgfileupload",method=RequestMethod.POST)
	public String imgFileUpload(Model model,@CookieValue(value="SSROLC_ID") String regId,MultipartHttpServletRequest mhRequest){
		
		final String boardTable = "popup";
		String uploadPath = boardUploadPath+File.separator+boardTable+File.separator+"editor";
		
		final String imageFileType = "image";
		
		FileUploadUtil fileUploadUtil = new FileUploadUtil(mhRequest, imageFileType
				, uploadPath, 0,new ArrayList<UploadFileInfo>());
		
		List<UploadFileInfo> uploadFileInfoList = fileUploadUtil.doFileUpload();
		
		int attachFileNo = 0;
		
		for (UploadFileInfo uploadFileInfo : uploadFileInfoList) {
			AttachFile attachFile = new AttachFile(boardTable,0,true,1,uploadFileInfo.getOriginalFilename()
					, uploadFileInfo.getConvertFileName(),0,uploadFileInfo.getSize()
					,uploadFileInfo.getWidth(),uploadFileInfo.getHeight()
					,uploadFileInfo.getFileType(),uploadFileInfo.isThumbType(), regId
					,mhRequest.getRemoteAddr(),new Timestamp(new Date().getTime()));
			
			boardService.addAttachFile(attachFile);
			
			attachFileNo = attachFile.getAttachFileNo();
		}

		String url = "?callback_result=";

		if(attachFileNo == 0){
			url += "error&errstr=yes";
		}else{
			url += "success";
			url += "&errstr=";
			url += "&imageURL=/crosseditor/photos/"+boardTable+"/"+attachFileNo;
			url += "&imageTitle="+mhRequest.getParameter("imageTitle");
			url += "&imageAlt="+mhRequest.getParameter("imageAlt");
			url += "&imageWidth="+mhRequest.getParameter("imageWidth");
			url += "&imageWidthUnit="+mhRequest.getParameter("imageWidthUnit");
			url += "&imageHeight="+mhRequest.getParameter("imageHeight");
			url += "&imageHeightUnit="+mhRequest.getParameter("imageHeightUnit");
			url += "&imageAlign="+mhRequest.getParameter("imageAlign");
			url += "&imageBorder="+mhRequest.getParameter("imageBorder");
			url += "&imageKind="+mhRequest.getParameter("imageKind");
			url += "&imageOrgPath="+mhRequest.getParameter("imageOrgPath");
			url += "&imageOrgWidth="+mhRequest.getParameter("imageOrgWidth");
			url += "&imageOrgHeight="+mhRequest.getParameter("imageOrgHeight");
			url += "&editorFrame="+mhRequest.getParameter("editorFrame");
		}

		return "redirect:/ssrolcmanager/crosseditor/callback"+url;
	}




	//###################################################################################################
	//팝업 테스트 - 프론트 사용 예정
	@RequestMapping(value={"/ssrolcmanager/popup/test/{aidx:[0-9]+}"},method={RequestMethod.GET,RequestMethod.HEAD})
	public String popupTest(Model model,@PathVariable int aidx){
		logger.debug("====================================popup Test");

		//데이터읽어오기
		Popup popup = popupService.getPopup(aidx);
		model.addAttribute("popup",popup);

		return "ssrolcmanager/popups/popupTest";
	}
	//###################################################################################################
}
