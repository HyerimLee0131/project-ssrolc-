package com.ssrolc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssrolc.domain.CenterSearch.CenterSearch;
import com.ssrolc.repository.CenterSearchRepository;
import com.ssrolc.repository.LearningCenterRepository;
import com.ssrolc.repository.MssqlRepository;

@Service
public class CenterSearchService {

	@Autowired
	private MssqlRepository mssqlRepository;

	@Autowired
	private LearningCenterRepository learningCenterRepository;

	@Autowired
	private CenterSearchRepository centerSearchRepository;

	//가맹센터리스트
	public List<CenterSearch> getCenters(String area){
		List<CenterSearch> centers = mssqlRepository.searchCenters(area);
		List<Map<String, Object>> url = learningCenterRepository.findCentersUrl();

		//ms센터 정보와 my홈페이지url 정보 조합
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

	//직영센터리스트
	public List<CenterSearch> getDirectCenters(String area){
		Map<String, Object> map = new HashMap<>();
		map.put("area", area);

		return centerSearchRepository.findDirectCenters(map);
	}

	//시/도 정보
	public List<String> getDos(String doName, String si, String dong, String jobflag){
		Map<String, Object> map = new HashMap<>();
		map.put("doName", doName);
		map.put("si", si);
		map.put("dong", dong);
		map.put("jobflag", jobflag);

		return mssqlRepository.findDos(map);
	}


}
