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
	public Disclosure getDisclosureByIdx(int aidx) {

		return disclosureRepository.findDisclosure(aidx);
	}
	/*
	 * 정보공개서 열람한 사람들의 리스트
	 * */
	public List<Disclosure> getDisclosures(int startLimit,int endLimit){
		Map<String,Object> map = new HashMap<>();
		map.put("startLimit",startLimit);
		map.put("endLimit",endLimit);
		
		return disclosureRepository.findDisclosures(map);
	}
	/*
	 * 정보공개서 열람한 사람들의 리스트(검색)
	 * */
//	public List<Disclosure> getDisclosureInfo(int startLimit,int endLimit){
//		Map<String,Object> map = new HashMap<>();
//		map.put("startLimit",startLimit);
//		map.put("endLimit",endLimit);
//		
//		
//		return disclosureRepository.findDisclosures(map);
//	}
	/*
	 * 정보공개서 열람 총 건수 
	 * */
	public int getDisclosureCnt(){
		return disclosureRepository.countDisclosure();
	}
	/*
	 * 정보공개서를 검색한 총 건수
	 * */
//	public int getSearchDisclosureCnt() {
//		
//		return disclosureRepository.findSearchDisclosureCnt();
//	}

}
