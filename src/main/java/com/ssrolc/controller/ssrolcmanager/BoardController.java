package com.ssrolc.controller.ssrolcmanager;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.util.FileCopyUtils;
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
import com.ssrolc.domain.board.BoardCategory;
import com.ssrolc.exception.ArticleNotAddException;
import com.ssrolc.exception.ArticleNotFoundException;
import com.ssrolc.exception.BoardCategoryNotFoundException;
import com.ssrolc.exception.BoardNotFoundException;
import com.ssrolc.exception.BoardNotUploadException;
import com.ssrolc.service.BoardService;
import com.ssrolc.utils.FileUploadUtil;
import com.ssrolc.utils.PageUtil;

@Controller
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService boardService;

	@Value("${uploadpath.boards}")
	private String boardUploadPath;
	
	@RequestMapping(value={"/ssrolcmanager/boards/{boardTable}"} , method =  { RequestMethod.GET, RequestMethod.HEAD })
	public String list(Model model,@PathVariable String boardTable) {
		
		if(Strings.isNullOrEmpty(boardTable)){
			throw new BoardNotFoundException(boardTable);
		}

		Board boardInfo = boardService.getBoardInfo(boardTable);
		
		if(boardInfo == null || boardInfo.equals(null)){
			throw new BoardNotFoundException(boardTable);
		}else{
			model.addAttribute("boardInfo",boardInfo);

			if(boardInfo.isBoardCategoryEnable()){
				List<BoardCategory> boardCategoryList = boardService.getBoardCategorys(boardTable);
				
				if(boardCategoryList == null || boardCategoryList.equals(null)){
					throw new BoardCategoryNotFoundException(boardTable);
				}else{
					model.addAttribute("boardCategoryList",boardCategoryList);
				}
			}
			
			//해더에 스크립트 추가
			List<String> headerScript = new ArrayList<>();
			headerScript.add("ssrolcmanager/boards/"+boardTable+"List");
			
			model.addAttribute("headerScript",headerScript);
			
			return "ssrolcmanager/boards/"+boardTable+"List";
		}
	}
	
	
	@RequestMapping(value={"/ssrolcmanager/boards/{boardTable}/{pageNum:[0-9]+}"} ,
			method = { RequestMethod.GET, RequestMethod.HEAD })
	@ResponseBody
	public ResponseEntity<Map<String,Object>> listJson(@PathVariable String boardTable,@PathVariable int pageNum){
		Board boardInfo = boardService.getBoardInfo(boardTable);
		if(boardInfo == null || boardInfo.equals(null)){
			throw new BoardNotFoundException(boardTable);
		}else{
			int rowBlockSize = boardInfo.getPageBlockSize();
			int pageBlockSize = boardInfo.getRowBlockSize();
			int totalRowCnt = boardService.getArticleCnt(boardTable);
			
			PageUtil pageUtil = new	PageUtil(pageNum, totalRowCnt, rowBlockSize, pageBlockSize);
			
			Map<String,Object> map = new HashMap<>();
			map.put("pageInfo",pageUtil);
			map.put("articles",boardService.getArticles(boardTable,pageUtil.getStartRow(),pageUtil.getEndRow()));
			return ResponseEntity.ok(map);
		}
	}
	
	@RequestMapping(value={"/ssrolcmanager/boards/{boardTable}/{pageNum:[0-9]+}/{searchField}/{searchValue}"} ,
			method = { RequestMethod.GET, RequestMethod.HEAD })
	@ResponseBody
	public ResponseEntity<Map<String,Object>> searchListJson(@PathVariable String boardTable,@PathVariable int pageNum,
			@PathVariable String searchField,@PathVariable String searchValue){
		Board boardInfo = boardService.getBoardInfo(boardTable);
		if(boardInfo == null || boardInfo.equals(null)){
			throw new BoardNotFoundException(boardTable);
		}else{
			int rowBlockSize = boardInfo.getPageBlockSize();
			int pageBlockSize = boardInfo.getRowBlockSize();
			int totalRowCnt = boardService.getArticleCnt(boardTable,searchField,searchValue);
			
			PageUtil pageUtil = new	PageUtil(pageNum, totalRowCnt, rowBlockSize, pageBlockSize);
			
			Map<String,Object> map = new HashMap<>();
			map.put("pageInfo",pageUtil);
			map.put("articles",boardService.getArticles(boardTable,pageUtil.getStartRow(),pageUtil.getEndRow(),searchField,searchValue));
			return ResponseEntity.ok(map);
		}
	}
	
	
	@RequestMapping(value={"/ssrolcmanager/boards/{boardTable}/{categoryCode}/{pageNum:[0-9]+}"} ,
			method = { RequestMethod.GET, RequestMethod.HEAD })
	@ResponseBody
	public ResponseEntity<Map<String,Object>> listWithCategoryJson(@PathVariable String boardTable,@PathVariable String categoryCode,@PathVariable int pageNum){
		Board boardInfo = boardService.getBoardInfo(boardTable);
		if(boardInfo == null || boardInfo.equals(null)){
			throw new BoardNotFoundException(boardTable);
		}else{
			int rowBlockSize = boardInfo.getPageBlockSize();
			int pageBlockSize = boardInfo.getRowBlockSize();
			int totalRowCnt = boardService.getArticleCnt(boardTable,categoryCode);
			
			PageUtil pageUtil = new	PageUtil(pageNum, totalRowCnt, rowBlockSize, pageBlockSize);
			
			Map<String,Object> map = new HashMap<>();
			map.put("pageInfo",pageUtil);
			map.put("articles",boardService.getArticles(boardTable,categoryCode,pageUtil.getStartRow(),pageUtil.getEndRow()));
			
			if(boardInfo.isBoardCategoryEnable()){
				List<BoardCategory> boardCategoryList = boardService.getBoardCategorys(boardTable);
				
				if(boardCategoryList == null || boardCategoryList.equals(null)){
					throw new BoardCategoryNotFoundException(boardTable);
				}
				
				Map<String,Object> cateMap = new HashMap<>();
				
				for (BoardCategory boardCategory : boardCategoryList) {
					cateMap.put(boardCategory.getCategoryCode(),boardCategory.getCategoryName());
				}
				
				map.put("boardCategoryMap",cateMap);
			}
			return ResponseEntity.ok(map);
		}
	}
	
	@RequestMapping(value={"/ssrolcmanager/boards/{boardTable}/{categoryCode}/{pageNum:[0-9]+}/{searchField}/{searchValue}"} ,
			method = { RequestMethod.GET, RequestMethod.HEAD })
	@ResponseBody
	public ResponseEntity<Map<String,Object>> searchListWithCategoryJson(@PathVariable String boardTable,@PathVariable String categoryCode
			,@PathVariable int pageNum,@PathVariable String searchField,@PathVariable String searchValue){
		Board boardInfo = boardService.getBoardInfo(boardTable);
		if(boardInfo == null || boardInfo.equals(null)){
			throw new BoardNotFoundException(boardTable);
		}else{
			int rowBlockSize = boardInfo.getPageBlockSize();
			int pageBlockSize = boardInfo.getRowBlockSize();
			int totalRowCnt = boardService.getArticleCnt(boardTable,categoryCode,searchField,searchValue);
			
			PageUtil pageUtil = new	PageUtil(pageNum, totalRowCnt, rowBlockSize, pageBlockSize);
			
			Map<String,Object> map = new HashMap<>();
			map.put("pageInfo",pageUtil);
			map.put("articles",boardService.getArticles(boardTable,categoryCode,pageUtil.getStartRow(),pageUtil.getEndRow(),searchField,searchValue));
			return ResponseEntity.ok(map);
		}
	}
	
	
	@RequestMapping(value={"/ssrolcmanager/board/{boardTable}/{articleNo:[0-9]+}"},method = { RequestMethod.GET, RequestMethod.HEAD })	
	public String view(Model model,@PathVariable String boardTable,@PathVariable int articleNo){
		Board boardInfo = boardService.getBoardInfo(boardTable);
		if(boardInfo == null || boardInfo.equals(null)){
			throw new BoardNotFoundException(boardTable);
		}else{
			Article article = boardService.getArticle(boardTable, articleNo);
			if(article == null || article.equals(null)){
				throw new ArticleNotFoundException(boardTable,articleNo);
			}else{
				model.addAttribute("boardInfo",boardInfo);
				model.addAttribute("article",article);
				
				if(boardInfo.isBoardFileUploadEnable()){
					final String fileFormat = "M";

					List<AttachFile> attachFiles = boardService.getAttachFiles(boardTable, articleNo, fileFormat);
					
					model.addAttribute("attachFiles",attachFiles);
				}
				
				if(boardInfo.isBoardCategoryEnable()){
					model.addAttribute("boardCategoryName",boardService.getBoardCategoryName(boardTable,article.getCategoryCode()));
				}
				
				
				boardService.setArticleHitUp(boardTable,articleNo);
				
				//해더에 스크립트 추가
				List<String> headerScript = new ArrayList<>();
				headerScript.add("ssrolcmanager/boards/"+boardTable+"View");
				
				model.addAttribute("headerScript",headerScript);
				
				return "ssrolcmanager/boards/"+boardTable+"View";
			}
		}
	}
	
	@RequestMapping(value={"/ssrolcmanager/board/{boardTable}/edit/{articleNo:[0-9]+}"},method = { RequestMethod.GET, RequestMethod.HEAD })	
	public String edit(Model model,@PathVariable String boardTable,@PathVariable int articleNo){
		Board boardInfo = boardService.getBoardInfo(boardTable);
		if(boardInfo == null || boardInfo.equals(null)){
			throw new BoardNotFoundException(boardTable);
		}else{
			Article article = boardService.getArticle(boardTable, articleNo);
			if(article == null || article.equals(null)){
				throw new ArticleNotFoundException(boardTable,articleNo);
			}else{
				model.addAttribute("boardInfo",boardInfo);
				model.addAttribute("article",article);
				
				if(boardInfo.isBoardFileUploadEnable()){
					final String fileFormat = "M";

					List<AttachFile> attachFiles = boardService.getAttachFiles(boardTable, articleNo, fileFormat);
					
					model.addAttribute("attachFiles",attachFiles);
				}
				
				if(boardInfo.isBoardCategoryEnable()){
					List<BoardCategory> boardCategoryList = boardService.getBoardCategorys(boardTable);
					
					if(boardCategoryList == null || boardCategoryList.equals(null)){
						throw new BoardCategoryNotFoundException(boardTable);
					}else{
						model.addAttribute("boardCategoryList",boardCategoryList);
					}
				}
				
				
				//해더에 스크립트 추가
				List<String> headerScript = new ArrayList<>();
				headerScript.add("crosseditor30/js/namo_scripteditor");
				headerScript.add("ssrolcmanager/boards/"+boardTable+"Edit");
				
				model.addAttribute("headerScript",headerScript);
				
				return "ssrolcmanager/boards/"+boardTable+"Edit";
			}
		}
	}
	
	@RequestMapping(value={"/ssrolcmanager/board/{boardTable}/{articleNo:[0-9]+}"},method = { RequestMethod.POST })	
	public String modify(Model model,@CookieValue(value="SSROLC_ID") String regId
					,@PathVariable String boardTable
					,@PathVariable int articleNo
					,@RequestParam(value="boardCategoryCode",defaultValue="1") String categoryCode
					,@RequestParam(value="boardTitle") String title
					,@RequestParam(value="boardContent") String content
					,@RequestParam(value="etc1", required=false) String etc1
					,@RequestParam(value="etc2", required=false) String etc2
					,@RequestParam(value="etc3", required=false) String etc3
					,@RequestParam(value="deleteAttachFileNo") int deleteAttachFileNo 
					,MultipartHttpServletRequest mhRequest){
		
		Board boardInfo = boardService.getBoardInfo(boardTable);
		if(boardInfo == null || boardInfo.equals(null)){
			throw new BoardNotFoundException(boardTable);
		}else{
			Article article = boardService.getArticle(boardTable, articleNo);
			if(article == null || article.equals(null)){
				throw new ArticleNotFoundException(boardTable,articleNo);
			}else{
				
				Timestamp nowTime = new Timestamp(new Date().getTime());
				
				boardService.setArticle(boardTable, articleNo, categoryCode
						, title, content, etc1, etc2, etc3, nowTime);
				
				String uploadPath = boardUploadPath+File.separator+boardTable;
				
				if(deleteAttachFileNo > 0){
					AttachFile attachFileInfo = boardService.getAttachFile(deleteAttachFileNo);					
					
					String filePath = uploadPath+File.separator+attachFileInfo.getConvertFileName();
					
					File file = new File(filePath);
					
					if(file.exists()){
						file.delete();
						boardService.removeAttachFile(deleteAttachFileNo);
					}					
					
					FileUploadUtil fileUploadUtil = new FileUploadUtil(mhRequest, boardInfo.getBoardFileUploadType()
							, uploadPath,new ArrayList<AttachFile>(),boardTable,articleNo, false, "M"
							, regId, mhRequest.getRemoteAddr(),nowTime);
					
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
					
					boardService.setArticleFileCnt(articleNo, fileCnt, imageCnt);
					
				}else{
					if(article.getFileCnt() == 0  && article.getImageCnt() == 0 ){
						FileUploadUtil fileUploadUtil = new FileUploadUtil(mhRequest, boardInfo.getBoardFileUploadType()
								, uploadPath,new ArrayList<AttachFile>(),boardTable,articleNo, false, "M"
								, regId, mhRequest.getRemoteAddr(),nowTime);
						
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
						
						boardService.setArticleFileCnt(articleNo, fileCnt, imageCnt);
					}
				}
								
				return "redirect:/ssrolcmanager/boards/"+boardTable;
			}
		}
	}
	
	
	@RequestMapping(value="/ssrolcmanager/boards/{boardTable}/new",method={ RequestMethod.GET, RequestMethod.HEAD })
	public String write(Model model,@PathVariable String boardTable){
		Board boardInfo = boardService.getBoardInfo(boardTable);
		if(boardInfo == null || boardInfo.equals(null)){
			throw new BoardNotFoundException(boardTable);
		}else{
			model.addAttribute("boardInfo",boardInfo);
			
			if(boardInfo.isBoardCategoryEnable()){
				List<BoardCategory> boardCategoryList = boardService.getBoardCategorys(boardTable);
				
				if(boardCategoryList == null || boardCategoryList.equals(null)){
					throw new BoardCategoryNotFoundException(boardTable);
				}else{
					model.addAttribute("boardCategoryList",boardCategoryList);
				}
			}

			//해더에 스크립트 추가
			List<String> headerScript = new ArrayList<>();
			headerScript.add("crosseditor30/js/namo_scripteditor");
			headerScript.add("ssrolcmanager/boards/"+boardTable+"Write");
			
			model.addAttribute("headerScript",headerScript);
			
			return "ssrolcmanager/boards/"+boardTable+"Write";
		}
	}
	
	@RequestMapping(value="/ssrolcmanager/boards/{boardTable}/{articleNo:[0-9]+}",method=RequestMethod.DELETE)
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public void delete(@PathVariable String boardTable,@PathVariable int articleNo){
		List<AttachFile> attachFileList =  boardService.getAttachFiles(boardTable, articleNo,"");
		
		boardService.removeArticle(boardTable, articleNo);
		boardService.removeAttachFilesToArticle(boardTable, articleNo);
		
		String uploadPath = boardUploadPath+File.separator+boardTable;
		
		for (AttachFile attachFile : attachFileList) {
			String filePath = uploadPath+File.separator+attachFile.getConvertFileName();
			
			File file = new File(filePath);
			
			if(file.exists()){
				file.delete();
			}
		}
	}
	
	@RequestMapping(value="/ssrolcmanager/boards/{boardTable}",method=RequestMethod.POST)
	public String addArticle(Model model,@CookieValue(value="SSROLC_ID") String regId,@PathVariable String boardTable
							,@RequestParam(value="boardCategoryCode",defaultValue="1") String categoryCode
							,@RequestParam(value="boardTitle") String title
							,@RequestParam(value="boardContent") String content
							,@RequestParam(value="etc1", required=false) String etc1
							,@RequestParam(value="etc2", required=false) String etc2
							,@RequestParam(value="etc3", required=false) String etc3
							,MultipartHttpServletRequest mhRequest){

		Board boardInfo = boardService.getBoardInfo(boardTable);
		if(boardInfo == null || boardInfo.equals(null)){
			throw new BoardNotFoundException(boardTable);
		}else{
			if(Strings.isNullOrEmpty(title) || Strings.isNullOrEmpty(content)){
				throw new ArticleNotAddException(boardTable);
			}
			
			Timestamp nowDate = new Timestamp(new Date().getTime());
			
			Article article = new Article(boardTable, categoryCode, title, content,0,0,0
					, etc1, etc2, etc3, true, false, regId, mhRequest.getRemoteAddr(), nowDate, nowDate);
			//,"","","",true, false, regId, mhRequest.getRemoteAddr(), nowDate, nowDate);
			
			boardService.addArticle(article);
			
			int lastArticleNo = article.getArticleNo();
			
			if(boardInfo.isBoardFileUploadEnable()){
				
				String uploadPath = boardUploadPath+File.separator+boardTable;
				
				FileUploadUtil fileUploadUtil = new FileUploadUtil(mhRequest, boardInfo.getBoardFileUploadType()
						, uploadPath,new ArrayList<AttachFile>(),boardTable,lastArticleNo, false, "M"
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
				
			}
			
			return "redirect:/ssrolcmanager/boards/"+boardTable;
		}
	}
	
	
	
	@RequestMapping(value="/ssrolcmanager/boards/{boardTable}/imgfileupload",method=RequestMethod.POST)
	public String imgFileUpload(Model model,@CookieValue(value="SSROLC_ID") String regId,@PathVariable String boardTable,MultipartHttpServletRequest mhRequest){
		Board boardInfo = boardService.getBoardInfo(boardTable);
		if(boardInfo == null || boardInfo.equals(null)){
			throw new BoardNotFoundException(boardTable);
		}else{
			if(!boardInfo.isBoardFileUploadEnable()){
				throw new BoardNotUploadException(boardTable);
			}else{
					String uploadPath = boardUploadPath+File.separator+boardTable;
					
					FileUploadUtil fileUploadUtil = new FileUploadUtil(mhRequest, "image"
							, uploadPath,new ArrayList<AttachFile>(),boardTable, 0, true, "M"
							, regId, mhRequest.getRemoteAddr(),new Timestamp(new Date().getTime()));
					
				List<AttachFile> uploadedAttachFileList = fileUploadUtil.doFileUpload();	
				
				int attachFileNo = 0;
				
				for (AttachFile attachFile : uploadedAttachFileList) {
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
		}
	}
	
	@RequestMapping(value="/ssrolcmanager/crosseditor/callback",method={RequestMethod.GET,RequestMethod.HEAD})
	public String crosseditorCallback(Model model){
		return "ssrolcmanager/boards/crosseditorCallback";
	}
	
	@RequestMapping(value="/crosseditor/photos/{boardTable}/{attachFileNo:[0-9]+}",method={RequestMethod.GET} ,produces={MediaType.IMAGE_GIF_VALUE,MediaType.IMAGE_JPEG_VALUE,MediaType.IMAGE_PNG_VALUE})
	@ResponseBody
	public ResponseEntity<InputStreamResource> crosseditorPhotoStream(HttpServletResponse res,@PathVariable String boardTable,@PathVariable int attachFileNo) throws FileNotFoundException{
		AttachFile attachFile = boardService.getEditorAttachFile(boardTable,attachFileNo);
		
		String imageFilePath = boardUploadPath+File.separator+boardTable+File.separator+attachFile.getConvertFileName();
		
		File imageFile = new File(imageFilePath);
		
		FileInputStream fis = new FileInputStream(imageFile);
		
		return ResponseEntity.ok()
				.contentLength(attachFile.getFileSize())
				.body(new InputStreamResource(fis));
	}
	
	
	@RequestMapping(value="/download/{attachFileNo:[0-9]+}",method={RequestMethod.GET})
	public void downloadFile(HttpServletRequest request,HttpServletResponse response,@PathVariable int attachFileNo) throws IOException{
		AttachFile attachFile = boardService.getAttachFile(attachFileNo);
		
		String filePath = boardUploadPath+File.separator+attachFile.getBoardTable()+File.separator+attachFile.getConvertFileName();
		
		File downloadFile = new File(filePath);
		
		if(!downloadFile.exists()){
			//에러처리
			return;
		}
		
		Path source = Paths.get(filePath);
		String downloadMimType = Files.probeContentType(source);
		
		if(Strings.isNullOrEmpty(downloadMimType)){
			downloadMimType = "application/octet-stream";
		}
		
		String fileName = attachFile.getFileName();
		String browser = getBrowser(request);
        if (browser.contains("MSIE")) {
            fileName = URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", " ");
        } else if (browser.contains("Firefox")) {
               fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
        } else if (browser.contains("Opera")) {
               fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
        } else if (browser.contains("Chrome")) {
               fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
        }
		
		response.setContentType(downloadMimType);
		response.setHeader("Content-Transfer-Encoding", "binary;");
		response.setHeader("Pragma", "no-cache;");
		response.setHeader("Expires", "-1;");
		response.setHeader("Content-Disposition",String.format("attachment; filename=\"%s\";",fileName));
		
		response.setContentLength((int)downloadFile.length());
		
		InputStream inputStream = new BufferedInputStream(new FileInputStream(downloadFile));
		
		FileCopyUtils.copy(inputStream,response.getOutputStream());
		
		boardService.setAttachFileDownloadCntUp(attachFileNo);
		
	}
	
	/**
	 * 브라우저 정보를 가져오는 메서드
	 * @param request
	 */
	private String getBrowser(HttpServletRequest request) {
        String header =request.getHeader("User-Agent");
        if (header.contains("MSIE")||header.contains("Trident/7.0")) {
               return "MSIE";
        } else if(header.contains("Chrome")) {
               return "Chrome";
        } else if(header.contains("Opera")) {
               return "Opera";
        }
        return "Firefox";
  }
	
}
