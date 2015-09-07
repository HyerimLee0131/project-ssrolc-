package com.ssrolc.repository;

import java.util.List;
import java.util.Map;

import com.ssrolc.domain.CenterSearch.CenterSearch;
import com.ssrolc.domain.franchise.FranchiseCity;
import com.ssrolc.domain.franchise.Franchises;
import com.ssrolc.domain.learningcenter.LearningCenter;


@AnotherRepositoryAnnoInterface
public interface MssqlRepository {
	public String findCenter(String deptId1);

	public List<LearningCenter> findCenters(String area);
	/*
	 * 우리동네 러닝센터 찾기 리스트
	 * */
	public List<CenterSearch> searchCenters(String area);
	/*
	 * 우리동네 러닝센터 찾기 도 리스트(셀렉트박스)
	 * */
	public List<String> findDos(Map<String, Object> map);
	/*
	 * 가맹지사 찾기
	 * */
	public List<Franchises> findFranchises(Map<String, Object> map);
	/*
	 * 가맹지사 군/구 찾기
	 * */
	public List<FranchiseCity> findFranchisesCity(Map<String, Object> map);
	
}
