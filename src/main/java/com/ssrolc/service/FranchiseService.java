package com.ssrolc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssrolc.domain.franchise.Franchise;
import com.ssrolc.repository.FranchiseRepository;

@Service
public class FranchiseService {

	@Autowired
	private FranchiseRepository franchiseRepository;

	/*
	 * 가맹문의 상담 접수 대기건 리스트(검색 포함)
	 * */
	public List<Franchise> getSearchFranchises(String jslcArea1, String jslcArea2,String jslcType,
			String startDate,String endDate,String jslcounseling,String memName, int startLimit,int endLimit){
		Map<String,Object> map = new HashMap<>();
		String[] jslTypeArray = jslcType.split(",");
		List<String> jslTypeList = null;
		if(!"".equals(jslcType)){
			jslTypeList = new ArrayList<String>();
			for (int i = 0; i < jslTypeArray.length; i++) {
				jslTypeList.add(jslTypeArray[i]);
			}
		}
		map.put("jslTypeList", jslTypeList);
		map.put("jslcArea1", jslcArea1);
		map.put("jslcArea2", jslcArea2);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("jslcounseling",jslcounseling);
		map.put("memName", memName);
		map.put("startLimit",startLimit);
		map.put("endLimit",endLimit);
		
		
		return franchiseRepository.findFranchises(map);
	}
	
	/*
	 * 가맹문의 등록하기
	 * */
	public void insertFranchise(Franchise franchise){
		 franchiseRepository.insertFranchises(franchise);
	}
	/*
	 * 정보공개서를 검색한 총 건수
	 * */
	public int getSearchDisclosureCnt(String jslcArea1,String jslcArea2,String jslcType,
			String startDate,String endDate,String jslcounseling,String memName) {
		Map<String,Object> map = new HashMap<>();
		String[] jslTypeArray = jslcType.split(",");
		List<String> jslTypeList = null;
		if(!"".equals(jslcType)){
			jslTypeList = new ArrayList<String>();
			for (int i = 0; i < jslTypeArray.length; i++) {
				jslTypeList.add(jslTypeArray[i]);
			}
		}
		map.put("jslTypeList", jslTypeList);
		map.put("jslcArea1", jslcArea1);
		map.put("jslcArea2", jslcArea2);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("jslcounseling", jslcounseling);
		map.put("memName", memName);
		
		return franchiseRepository.countSearchFranchises(map);
	}
	/*
	 * 광역시 리스트가져오기
	 * */
	public List<String> getFranchiseCityList() {
		
		return franchiseRepository.findFranchiseCityList();
	}
	/*
	 * 광역시에 따른 시,구 리스트 가져오기
	 * */
	public List<String> getFranchiseDeptAreaList(String jslcArea1) {

		return franchiseRepository.findFranchiseDeptAreaList(jslcArea1);
	}
	/*
	 * 접수상태 변경하기
	 * */
	public void getchangeJoinState(String jslcId, String jslcounseling) {
		Map<String,Object> map = new HashMap<>();
		map.put("jslcId", jslcId);
		map.put("jslcounseling", jslcounseling);
		franchiseRepository.findchangeJoinState(map);
	}

}
