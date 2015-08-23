package com.ssrolc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssrolc.domain.board.Article;
import com.ssrolc.domain.popup.Popup;
import com.ssrolc.repository.PopupRepository;

@Service
public class PopupService {

	@Autowired
	private PopupRepository popupRepository;
	
	public List<Popup> getPopups(int startLimit,int endLimit){
		Map<String,Object> map = new HashMap<>();
		map.put("startLimit",startLimit);
		map.put("endLimit",endLimit);

		return popupRepository.findPopups(map);
	}
	
	public List<Popup> getPopups(int startLimit,int endLimit,String searchField,String searchValue){
		Map<String,Object> map = new HashMap<>();
		map.put("startLimit",startLimit);
		map.put("endLimit",endLimit);
		map.put("searchField",searchField);
		map.put("searchValue",searchValue);

		return popupRepository.findPopups(map);
	}
	
	public int getPopupCnt(){
		Map<String,Object> map = new HashMap<>();

		return popupRepository.countPopup(map);
	}
	
	public int getPopupCnt(String searchField,String searchValue){
		Map<String,Object> map = new HashMap<>();
		map.put("searchField",searchField);
		map.put("searchValue",searchValue);

		return popupRepository.countPopup(map);
	}
	
	public Popup getPopup(int aidx){
		return popupRepository.findPopup(aidx);
	}

	public int setPopupsDel(String aidxs){
		return popupRepository.deletePopups(aidxs);
	}
	
	public void addPopup(Popup popup, String writeType){
		
		if (writeType.equals("0")){
			popupRepository.insertPopup(popup);
		}else{
			popupRepository.updatePopup(popup);
		}
	}
}
