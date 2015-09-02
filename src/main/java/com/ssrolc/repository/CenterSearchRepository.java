package com.ssrolc.repository;

import java.util.List;
import java.util.Map;

import com.ssrolc.domain.CenterSearch.CenterSearch;

@PrimaryRepositoryAnnoInterface
public interface CenterSearchRepository {
	/*
	 * 직영센터 리스트 읽어오기
	 */
	public List<CenterSearch> findDirectCenters(Map<String,Object> map);
}