package com.ssrolc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	 * 정보공개서 열람한 사람들의 리스트
	 * */
//	public List<Disclosure> getDisclosures(int startLimit,int endLimit){
//		Map<String,Object> map = new HashMap<>();
//		map.put("startLimit",startLimit);
//		map.put("endLimit",endLimit);
//		
//		return disclosureRepository.findDisclosures(map);
//	}
	/*
	 * 정보공개서 열람한 사람들의 리스트(검색)
	 * */
	public List<Disclosure> getSearchDisclosures(String deptArea1, String deptArea2,String deptType,
			String startDate,String endDate,String memName, int startLimit,int endLimit){
		Map<String,Object> map = new HashMap<>();
		map.put("deptArea1", deptArea1);
		map.put("deptArea2", deptArea2);
		map.put("deptType", deptType);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("memName", memName);
		map.put("startLimit",startLimit);
		map.put("endLimit",endLimit);
		
		
		return disclosureRepository.findDisclosures(map);
	}
	/*
	 * 정보공개서 열람 총 건수 
	 * */
//	public int getDisclosureCnt(){
//		return disclosureRepository.countDisclosure();
//	}
	/*
	 * 정보공개서를 검색한 총 건수
	 * */
	public int getSearchDisclosureCnt(String deptArea1,String deptArea2,String deptType,
			String startDate,String endDate,String memName) {
		Map<String,Object> map = new HashMap<>();
		map.put("deptArea1", deptArea1);
		map.put("deptArea2", deptArea2);
		map.put("deptType", deptType);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("memName", memName);
		return disclosureRepository.countSearchDisclosure(map);
	}

}
