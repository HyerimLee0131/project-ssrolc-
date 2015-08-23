package com.ssrolc.service;

import java.sql.Timestamp;
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
	
	public List<Article> getArticles(String boardTable,int startLimit,int endLimit
									,String searchField,String searchValue){
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
	
	public void addAttachFile(AttachFile attachFile){
		boardRepository.insertAttachFile(attachFile);
	}
	
	public AttachFile getEditorAttachFile(String boardTable,int attachFileNo){
		Map<String,Object> map = new HashMap<>();
		map.put("boardTable",boardTable);
		map.put("attachFileNo",attachFileNo);
		
		return boardRepository.findAttachFile(map);
	}
	
	public void addArticle(Article article){
		boardRepository.insertArticle(article);
	}
	
	public void setArticleFileCnt(int articleNo ,int fileCnt,int imageCnt){
		Map<String,Object> map = new HashMap<>();
		map.put("articleNo",articleNo);
		map.put("fileCnt",fileCnt);
		map.put("imageCnt",imageCnt);
		
		boardRepository.updateArticleFileCnt(map);
	}
	
	public AttachFile getAttachFile(int attachFileNo){
		return boardRepository.findAttachFileById(attachFileNo);
	}
	
	public void setAttachFileDownloadCntUp(int attachFileNo){
		boardRepository.updateAttachFileDownloadCntUp(attachFileNo);
	}
	
	public void removeArticle(String boardTable,int articleNo){
		Map<String,Object> map = new HashMap<>();
		map.put("boardTable",boardTable);
		map.put("articleNo",articleNo);
		
		boardRepository.deleteArticle(map);
	}
	
	public void removeAttachFilesToArticle(String boardTable,int articleNo){
		Map<String,Object> map = new HashMap<>();
		map.put("boardTable",boardTable);
		map.put("articleNo",articleNo);
		
		boardRepository.deleteAttachFilesToArticle(map);
	}
	
	public void setArticle(String boardTable,int articleNo,String categoryCode
			,String title,String content,String etc1,String etc2,String etc3,Timestamp updateDate){
		Map<String,Object> map = new HashMap<>();
		map.put("boardTable",boardTable);
		map.put("articleNo",articleNo);
		map.put("categoryCode",categoryCode);
		map.put("title",title);
		map.put("content",content);
		map.put("etc1",etc1);
		map.put("etc2",etc2);
		map.put("etc3",etc3);
		map.put("updateDate",updateDate);
		
		boardRepository.updateArticle(map);
	}
	
	public void removeAttachFile(int attachFileNo){
		boardRepository.deleteAttachFile(attachFileNo);
	}

	public int getArticleCnt(String boardTable, String categoryCode) {

		Map<String,Object> map = new HashMap<>();
		map.put("boardTable",boardTable);
		map.put("categoryCode",categoryCode);
		return boardRepository.countArticle(map);
	}

	public List<Article> getArticles(String boardTable, String categoryCode,
			int startLimit,int endLimit) {
		
		Map<String,Object> map = new HashMap<>();
		map.put("boardTable",boardTable);
		map.put("categoryCode",categoryCode);
		map.put("startLimit",startLimit);
		map.put("endLimit",endLimit);
		
		return boardRepository.findArticles(map);
	}

	public int getArticleCnt(String boardTable, String categoryCode,
			String searchField, String searchValue) {
		
		Map<String,Object> map = new HashMap<>();
		map.put("boardTable",boardTable);
		map.put("categoryCode",categoryCode);
		map.put("searchField",searchField);
		map.put("searchValue",searchValue);
		
		return boardRepository.countArticle(map);
	}

	public List<Article> getArticles(String boardTable, String categoryCode,
			int startLimit, int endLimit, String searchField, String searchValue) {

		Map<String,Object> map = new HashMap<>();
		map.put("boardTable",boardTable);
		map.put("categoryCode",categoryCode);
		map.put("startLimit",startLimit);
		map.put("endLimit",endLimit);
		map.put("searchField",searchField);
		map.put("searchValue",searchValue);
		
		return boardRepository.findArticles(map);
	}

	public String getBoardCategoryName(String boardTable, String categoryCode) {
		Map<String,Object> map = new HashMap<>();
		map.put("boardTable",boardTable);
		map.put("categoryCode",categoryCode);
		return boardRepository.findBoardCategoryName(map);
	}
}
