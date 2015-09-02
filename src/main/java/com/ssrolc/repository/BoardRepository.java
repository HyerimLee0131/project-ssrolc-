package com.ssrolc.repository;

import java.util.List;
import java.util.Map;

import com.ssrolc.domain.board.Article;
import com.ssrolc.domain.board.ArticleAndAttachFile;
import com.ssrolc.domain.board.ArticleWithThumb;
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
	 * 글 리스트+썸네일 읽어오기
	 */
	public List<ArticleWithThumb> findArticlesWithThumb(Map<String,Object> map);
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
	/*
	 *  db에 파일 정보 저장
	 */
	public void insertAttachFile(AttachFile attachFile);
	/*
	 * 첨부파일 읽어오기
	 */
	public AttachFile findAttachFile(Map<String,Object> map);
	/*
	 *	글 등록 
	 */
	public void insertArticle(Article article);
	/*
	 *	글에 파일개수 업데이트 글에 이미지 개수 업데이트
	 */
	public void updateArticleFileCnt(Map<String,Object> map);
	/*
	 * 파일 정보 읽어오기 
	 */
	public AttachFile findAttachFileById(int attachFileNo);
	/*
	 * 파일 다운로드 수 증가
	 */
	public void updateAttachFileDownloadCntUp(int attachFileNo);
	/*
	 * 글 삭제
	 */
	public void deleteArticle(Map<String,Object> map);
	/*
	 * 글에 첨부된 파일들 삭제
	 */
	public void deleteAttachFilesToArticle(Map<String, Object> map);
	/*
	 * 글 수정
	 */
	public void updateArticle(Map<String,Object> map);
	/*
	 * 파일 정보 삭제
	 */
	public void deleteAttachFile(int attachFileNo);
	/*
	 * 카테고리명 읽기
	 */
	public String findBoardCategoryName(Map<String, Object> map);
	/*
	 * 	파일 최종sort 가져오기
	 */
	public Integer findAttachFileMaxSort(int articleNo);
	/*
	 * 관리자 메인 오늘 등록된 신규글 개수
	 */
	public int countNoticeArticleCurrent();
	
	public List<ArticleAndAttachFile> findArticleAndAttachFile(Map<String, Object> map);
}
