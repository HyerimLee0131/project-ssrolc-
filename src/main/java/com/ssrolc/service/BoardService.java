package com.ssrolc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssrolc.domain.board.Article;
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
	
	public List<Article> getArticles(String boardTable,int page){
		Map<String,Object> map = new HashMap<>();
		map.put("boardTable",boardTable);
		map.put("startLimit",1);
		map.put("endLimit",10);
		
		return boardRepository.findArticles(map);
	}
}
