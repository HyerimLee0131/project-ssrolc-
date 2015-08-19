package com.ssrolc.repository;

import java.util.List;
import java.util.Map;

import com.ssrolc.domain.board.Article;
import com.ssrolc.domain.board.AttachFile;
import com.ssrolc.domain.board.Board;
import com.ssrolc.domain.board.BoardCategory;


@PrimaryRepositoryAnnoInterface
public interface BoardRepository {
	/*
	 * 보드 정보 읽어오기
	 */
	public Board findBoardById(String boardTable);
	/*
	 * 보드 카테고리 읽어오기
	 */
	public List<BoardCategory> findBoardCategorys(Map<String,Object> map);
	/*
	 * 글 개수 읽어오기
	 */
	public int countArticle(Map<String,Object> map);
	/*
	 * 글 리스트 읽어오기
	 */
	public List<Article> findArticles(Map<String,Object> map);
	/*
	 * 글 읽어오기
	 */
	public Article findArticle(Map<String,Object> map);
	/*
	 * 글에 첨부파일 읽어오기
	 */
	public List<AttachFile> findAttachFiles(Map<String,Object> map);
	/*
	 * 조회수 증가
	 */
	public void updateArticleHitUp(Map<String,Object> map);
}
