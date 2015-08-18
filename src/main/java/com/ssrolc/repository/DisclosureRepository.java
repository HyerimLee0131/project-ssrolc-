package com.ssrolc.repository;

import java.util.List;
import java.util.Map;

import com.ssrolc.domain.disclosure.Disclosure;


@PrimaryRepositoryAnnoInterface
public interface DisclosureRepository {
	/*
	 * 정보공개서 열람증 열기(팝업)
	 */
	public Disclosure findDisclosure(int aidx);
	
	/*
	 *  정보공개서 열람한 사람들의 리스트
	 * */
	public List<Disclosure> findDisclosures(Map<String,Object> map);
	
	/*
	 * 정보공개서 열람한 총 사람 수 
	 * */
	public int findDisclosureCnt();
	/*
	 * 정보공개서 열람한 총 사람 수(검색) 
	 * */
	public int findSearchDisclosureCnt();
}
