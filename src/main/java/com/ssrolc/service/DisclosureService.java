package com.ssrolc.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.ssrolc.domain.disclosure.Disclosure;
import com.ssrolc.repository.DisclosureRepository;

@Service
public class DisclosureService {

	@Autowired
	private DisclosureRepository disclosureRepository;
	/*
	 * 정보공개서 열람증 열기(팝업)
	 * */
	public Disclosure getDisclosure(int aidx) {

		return disclosureRepository.findDisclosure(aidx);
	}
	/*
	 * 정보공개서 열람한 사람들의 리스트(검색)
	 * */
	public List<Disclosure> getSearchDisclosures(String deptArea1, String deptArea2,String deptType,
			String startDate,String endDate,String memName, int startLimit,int endLimit){
		Map<String,Object> map = new HashMap<>();
		List<String> deptTypeList = null;
		if(!Strings.isNullOrEmpty(deptType)){
			String[] deptTypeArray = deptType.split(",");
			
			deptTypeList = Arrays.asList(deptTypeArray);
			
		}
		map.put("deptTypeList", deptTypeList);
		map.put("deptArea1", deptArea1);
		map.put("deptArea2", deptArea2);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("memName", memName);
		map.put("startLimit",startLimit);
		map.put("endLimit",endLimit);
		
		
		return disclosureRepository.findDisclosures(map);
	}
	/*
	 * 정보공개서를 검색한 총 건수
	 * */
	public int getSearchDisclosureCnt(String deptArea1,String deptArea2,String deptType,
			String startDate,String endDate,String memName) {
		Map<String,Object> map = new HashMap<>();
		List<String> deptTypeList = null;
		if(!Strings.isNullOrEmpty(deptType)){
			String[] deptTypeArray = deptType.split(",");
			
			deptTypeList = Arrays.asList(deptTypeArray);

		}
		map.put("deptTypeList", deptTypeList);
		map.put("deptArea1", deptArea1);
		map.put("deptArea2", deptArea2);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("memName", memName);
		
		return disclosureRepository.countSearchDisclosure(map);
	}
	/*
	 * 광역시 리스트가져오기
	 * */
	public List<String> getDisclosureCityList() {
		
		return disclosureRepository.findDisclosureCityList();
	}
	/*
	 * 광역시에 따른 시,구 리스트 가져오기
	 * */
	public List<String> getDisclosureDeptAreaList(String deptArea1) {

		return disclosureRepository.findDisclosureDeptAreaList(deptArea1);
	}
	
	public int getNewDisclosureCnt(){
		return disclosureRepository.countDisclosureCurrent();
	}
	//메일 인증한 사람 정보 등록
	public void insertMailAuth(String pMemName, String mailAddress, String authKey, String jslIp) {
		Map<String,Object> map = new HashMap<>();
		
		map.put("pMemName", pMemName);
		map.put("mailAddress", mailAddress);
		map.put("authKey", authKey);
		map.put("jslIp", jslIp);

		disclosureRepository.insertMailAuth(map);
	}
	
	/*
	 * 정보공개서에서 가맹희망 등록
	 * */
	public void insertDisclosure(Disclosure disclosure) {
		disclosureRepository.insertDisclosures(disclosure);
		
	}
	
	//인증키 일치,불일치 비교
	public String isDisclosureEmailAuth(String authKey, String memName, String email) {
	
		Map<String,Object> map = new HashMap<>();
		map.put("memName", memName);
		map.put("email", email);
		map.put("authKey", authKey);
		int dbAuthKeyCnt = disclosureRepository.countDisclosureEmailAuth(map);
		if(dbAuthKeyCnt > 0){
			 return "authKeyOk";
		}else{
			 return "authKeyFail";
		}
		
	}
	
	
	public Disclosure getDisclosureInfo(String memName, String email) {
		Map<String, Object> map = new HashMap<>();
		map.put("memName", memName);
		map.put("email", email);
		return disclosureRepository.findDisclosureInfo(map);
		
	}

}
