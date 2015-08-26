package com.ssrolc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssrolc.domain.myungsimbogam.Myungsimbogam;
import com.ssrolc.repository.MainRepository;

@Service
public class MainService {

	@Autowired
	private MainRepository mainRepository;

	public Myungsimbogam getData(int weekOfYear) {
		
		return mainRepository.findData(weekOfYear);
	}
	
	


}
