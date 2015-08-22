package com.ssrolc.repository;

import java.util.List;
import java.util.Map;

import com.ssrolc.domain.learningcenter.LearningCenter;



@PrimaryRepositoryAnnoInterface
public interface LearningCenterRepository {

	/*
	 * 러닝센터 홈페이지 관리 URL
	 */
	public List<Map<String, Object>> findCentersUrl();

	public LearningCenter findLearnigCenter(String deptId1);

	public String findHomeUrl(String deptId1);

	public void insertUrl(LearningCenter learningCenter);

	public void updateUrl(LearningCenter learningCenter);
	
}
