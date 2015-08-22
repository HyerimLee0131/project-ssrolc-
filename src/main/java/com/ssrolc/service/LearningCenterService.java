package com.ssrolc.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssrolc.domain.learningcenter.LearningCenter;
import com.ssrolc.repository.LearningCenterRepository;
import com.ssrolc.repository.MssqlRepository;


@Service
public class LearningCenterService {

	@Autowired
	private MssqlRepository mssqlRepository;
	
	@Autowired
	private LearningCenterRepository learningCenterRepository;
	
	public List<LearningCenter> getCenters(String area){
		List<LearningCenter> centers = mssqlRepository.findCenters(area);
		List<Map<String, Object>> url = learningCenterRepository.findCentersUrl();
		//TODO collection 라이브러리 이용하도록 개선필요
		for (int i = 0; i < centers.size(); i++) {
			for (int j = 0; j < url.size(); j++) {
				if(centers.get(i).getDeptId1().equals((String)url.get(j).get("deptId"))){
					centers.get(i).setHomeUrl((String)url.get(j).get("homeUrl"));
					break;
				}else{
					centers.get(i).setHomeUrl("");
				}
			}
		}

		return centers;
	}

	public String getCenter(String deptId1) {
		return mssqlRepository.findCenter(deptId1);
		
	}

	public String getHomeUrl(String deptId1) {
		
		return learningCenterRepository.findHomeUrl(deptId1);
	}
	

	public void writeLearningCenter(LearningCenter learningCenter,
			String writeType) {
		if (writeType.equals("0")){
			learningCenterRepository.insertUrl(learningCenter);
		}else{
			learningCenterRepository.updateUrl(learningCenter);
		}
	}


	
}
