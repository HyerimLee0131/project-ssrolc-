package com.ssrolc.controller.ssrolcmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;
import com.ssrolc.domain.board.Article;
import com.ssrolc.domain.board.Board;
import com.ssrolc.domain.board.BoardCategory;
import com.ssrolc.exception.ArticleNotFoundException;
import com.ssrolc.exception.BoardCategoryNotFoundException;
import com.ssrolc.exception.BoardNotFoundException;
import com.ssrolc.service.BoardService;
import com.ssrolc.utils.PageUtil;

@Controller
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value={"/ssrolcmanager/boards/{boardTable}"} , method =  { RequestMethod.GET, RequestMethod.HEAD })
	public String list(Model model,@PathVariable String boardTable) {
		
		if(Strings.isNullOrEmpty(boardTable)){
			throw new BoardNotFoundException(boardTable);
		}

		logger.debug("boards "+boardTable+" List");
		
		Board boardInfo = boardService.getBoardInfo(boardTable);
		
		if(boardInfo == null || boardInfo.equals(null)){
			throw new BoardNotFoundException(boardTable);
		}else{
			model.addAttribute("boardInfo",boardInfo);

			if(boardInfo.getBoardCategoryEnable()){
				List<BoardCategory> boardCategoryList = boardService.getBoardCategorys(boardTable);
				
				if(boardCategoryList == null || boardCategoryList.equals(null)){
					throw new BoardCategoryNotFoundException(boardTable);
				}else{
					model.addAttribute("boardCategoryList",boardCategoryList);
				}
			}
			
			//해더에 스크립트 추가
			List<String> headerScript = new ArrayList<>();
			headerScript.add("ssrolcmanager/boards/list");
			
			model.addAttribute("headerScript",headerScript);
			
			return "ssrolcmanager/boards/"+boardTable+"List";
		}
	}
	
	
	@RequestMapping(value={"/ssrolcmanager/boards/{boardTable}/{pageNum}"} ,
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
	
	@RequestMapping(value={"/ssrolcmanager/boards/{boardTable}/{pageNum}/{searchField}/{searchValue}"} ,
			method = { RequestMethod.GET, RequestMethod.HEAD })
	@ResponseBody
	public ResponseEntity<Map<String,Object>> searchListJson(@PathVariable String boardTable,@PathVariable int pageNum,
			@PathVariable String searchField,@PathVariable String searchValue){
		logger.debug("searchField:"+searchField+",searchValue:"+searchValue);
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
	
	
	@RequestMapping(value={"/ssrolcmanager/board/{boardTable}/{articleNo}"},method = { RequestMethod.GET, RequestMethod.HEAD })	
	public String view(Model model,@PathVariable String boardTable,@PathVariable int articleNo){
		Article article = boardService.getArticle(boardTable, articleNo);
		if(article == null || article.equals(null)){
			throw new ArticleNotFoundException(boardTable,articleNo);
		}else{
			model.addAttribute("article",article);
			
			return "ssrolcmanager/boards/"+boardTable+"view";
		}
	}
}
