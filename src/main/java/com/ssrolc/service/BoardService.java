package com.ssrolc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssrolc.domain.board.Article;
import com.ssrolc.domain.board.AttachFile;
import com.ssrolc.domain.board.Board;
import com.ssrolc.domain.board.BoardCategory;
import com.ssrolc.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	public Board getBoardInfo(String boardTable){
		return boardRepository.findBoardById(boardTable);
	}
	
	public List<BoardCategory> getBoardCategorys(String boardTable){
		Map<String,Object> map = new HashMap<>();
		map.put("boardTable",boardTable);
		
		return boardRepository.findBoardCategorys(map);
	}
	
	public List<Article> getArticles(String boardTable,int startLimit,int endLimit){
		Map<String,Object> map = new HashMap<>();
		map.put("boardTable",boardTable);
		map.put("startLimit",startLimit);
		map.put("endLimit",endLimit);
		
		return boardRepository.findArticles(map);
	}
	
	public List<Article> getArticles(String boardTable,int startLimit,int endLimit,String searchField,String searchValue){
		Map<String,Object> map = new HashMap<>();
		map.put("boardTable",boardTable);
		map.put("startLimit",startLimit);
		map.put("endLimit",endLimit);
		map.put("searchField",searchField);
		map.put("searchValue",searchValue);
		
		return boardRepository.findArticles(map);
	}
	
	public Article getArticle(String boardTable,int articleNo){
		Map<String,Object> map = new HashMap<>();
		map.put("boardTable",boardTable);
		map.put("articleNo",articleNo);
		
		return boardRepository.findArticle(map);
	}
	
	public int getArticleCnt(String boardTable){
		Map<String,Object> map = new HashMap<>();
		map.put("boardTable",boardTable);
		
		return boardRepository.countArticle(map);
	}
	
	public int getArticleCnt(String boardTable,String searchField,String searchValue){
		Map<String,Object> map = new HashMap<>();
		map.put("boardTable",boardTable);
		map.put("searchField",searchField);
		map.put("searchValue",searchValue);
		
		return boardRepository.countArticle(map);
	}
	
	public List<AttachFile> getAttachFiles(String boardTable,int articleNo,String fileFormat){
		Map<String,Object> map = new HashMap<>();
		map.put("boardTable",boardTable);
		map.put("articleNo",articleNo);
		map.put("fileFormat",fileFormat);
		
		return boardRepository.findAttachFiles(map);
	}
	
	public void setArticleHitUp(String boardTable,int articleNo){
		Map<String,Object> map = new HashMap<>();
		map.put("boardTable",boardTable);
		map.put("articleNo",articleNo);
		
		boardRepository.updateArticleHitUp(map);
	}
	
}
