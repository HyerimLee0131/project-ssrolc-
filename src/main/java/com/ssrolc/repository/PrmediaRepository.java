package com.ssrolc.repository;

import java.util.List;
import java.util.Map;

import com.ssrolc.domain.prmedia.Prmedia;

@PrimaryRepositoryAnnoInterface
public interface PrmediaRepository {
	/*
	 * 글 개수 읽어오기
	 */
	public int countPrmedia(Map<String,Object> map);
	/*
	 * 글 리스트 읽어오기
	 */
	public List<Prmedia> findPrmedias(Map<String,Object> map);
	/*
	 * 글 읽어오기
	 */
	public Prmedia findPrmedia(int aidx);
	/*
	 * 조회수 증가
	 */
	public void updateHit(int aidx);
	/*
	 *	글 등록 
	 */
	public void insertPrmedia(Prmedia prmedia);
	/*
	 *	글 수정
	 */
	public void updatePrmedia(Prmedia prmedia);
	/*
	 * 썸네일 등록
	 */
	public void updateThumbPrmedia(Map<String,Object> map);
	/*
	 * 글 삭제
	 */
	public void deletePrmedia(int aidx);
	/*
	 * 로딩시 첫번째 홍보영상 url
	 */
	public String findPrmediaFirst();
	/*
	 * 글 읽어오기
	 */
	public String findPrmediaView(int aidx);
	
}