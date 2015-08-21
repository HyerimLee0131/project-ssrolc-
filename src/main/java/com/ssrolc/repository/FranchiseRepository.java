package com.ssrolc.repository;

import java.util.List;
import java.util.Map;

import com.ssrolc.domain.franchise.Franchise;

@PrimaryRepositoryAnnoInterface
public interface FranchiseRepository {
	
	/*
	 *  정보공개서 열람한 사람들의 리스트
	 * */
	public List<Franchise> findFranchises(Map<String,Object> map);
	
	/*
	 * 정보공개서 열람한 총 사람 수(검색) 
	 * */
	public int countSearchFranchises(Map<String,Object> map);
	/*
	 * 정보공개서 광역시 리스트 
	 * */
	public List<String> findFranchiseCityList();
	/*
	 * 정보공개서 시,구 리스트 
	 * */
	public List<String> findFranchiseDeptAreaList(String jslcArea1);
	/*
	 * 접수상태 변경하기
	 * */
	public void findchangeJoinState(Map<String,Object> map);
	/*
	 * 가맹문의 등록
	 * */
	public void insertFranchises(Franchise franchise);
}
