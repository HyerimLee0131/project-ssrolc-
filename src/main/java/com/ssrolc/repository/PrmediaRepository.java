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
	public Prmedia findPrmedia(Map<String,Object> map);
	/*
	 * 조회수 증가
	 */
	public int updateHit(Map<String,Object> map);
}