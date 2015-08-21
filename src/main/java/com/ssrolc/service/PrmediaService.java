package com.ssrolc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssrolc.domain.prmedia.Prmedia;
import com.ssrolc.repository.PrmediaRepository;

@Service
public class PrmediaService {

	@Autowired
	private PrmediaRepository prmediaRepository;
	
	public List<Prmedia> getPrmedias(int startLimit,int endLimit){
		Map<String,Object> map = new HashMap<>();
		map.put("startLimit",startLimit);
		map.put("endLimit",endLimit);

		return prmediaRepository.findPrmedias(map);
	}
	
	public List<Prmedia> getPrmedias(int startLimit,int endLimit,String searchField,String searchValue){
		Map<String,Object> map = new HashMap<>();
		map.put("startLimit",startLimit);
		map.put("endLimit",endLimit);
		map.put("searchField",searchField);
		map.put("searchValue",searchValue);

		return prmediaRepository.findPrmedias(map);
	}
	
	public int getPrmediaCnt(){
		Map<String,Object> map = new HashMap<>();

		return prmediaRepository.countPrmedia(map);
	}
	
	public int getPrmediaCnt(String searchField,String searchValue){
		Map<String,Object> map = new HashMap<>();
		map.put("searchField",searchField);
		map.put("searchValue",searchValue);

		return prmediaRepository.countPrmedia(map);
	}
	
	public Prmedia getPrmedia(int aidx){
		return prmediaRepository.findPrmedia(aidx);
	}

	public void setPrmediaHitUp(int aidx) {
		// TODO Auto-generated method stub
		prmediaRepository.updateHit(aidx);
	}
}
