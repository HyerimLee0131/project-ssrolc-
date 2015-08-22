package com.ssrolc.repository;

import java.util.List;
import java.util.Map;

import com.ssrolc.domain.popup.Popup;

@PrimaryRepositoryAnnoInterface
public interface PopupRepository {
	/*
	 * 글 개수 읽어오기
	 */
	public int countPopup(Map<String,Object> map);
	/*
	 * 글 리스트 읽어오기
	 */
	public List<Popup> findPopups(Map<String,Object> map);
	/*
	 * 글 읽어오기
	 */
	public Popup findPopup(Map<String,Object> map);
	/*
	 * 조회수 증가
	 */
	public int updateHit(Map<String,Object> map);
}