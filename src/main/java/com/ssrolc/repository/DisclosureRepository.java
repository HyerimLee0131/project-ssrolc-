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
	 * 정보공개서 열람한 총 사람 수(검색) 
	 * */
	public int countSearchDisclosure(Map<String,Object> map);
	/*
	 * 정보공개서 광역시 리스트 
	 * */
	public List<String> findDisclosureCityList();
	/*
	 * 정보공개서 시,구 리스트 
	 * */
	public List<String> findDisclosureDeptAreaList(String deptArea1);
	/*
	 * 관리자 메인 오늘자 정보열람 신청 개수
	 */
	public int countDisclosureCurrent();
	/*
	 * 메일인증한사람 등록하기
	 * */
	public void insertMailAuth(Map<String,Object> map);
	/*
	 * 수신거부한 사람 등록하기
	 * */
	public void insertIgnoreEmail(Map<String, Object> map);
	/*
	 * 가맹희망자 등록
	 * */
	public void insertDisclosures(Disclosure disclosure);
	/*
	 * 가맹희망자 수정
	 * */
	public void updateDisclosures(Disclosure disclosure);
	
	public int countDisclosureEmailAuth(Map<String, Object> map);

	public Disclosure findDisclosureInfo(Map<String, Object> map);

	public int countDisclosureEmailAuthYN(Map<String, Object> map);

	public int countIgnoreEmailYN(String email);

	

	
}
