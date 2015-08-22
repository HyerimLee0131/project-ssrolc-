package com.ssrolc.repository;

import java.util.List;

import com.ssrolc.domain.learningcenter.LearningCenter;


@AnotherRepositoryAnnoInterface
public interface MssqlRepository {
	public String findCenter(String deptId1);

	public List<LearningCenter> findCenters(String area);
}
