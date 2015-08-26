package com.ssrolc.repository;

import com.ssrolc.domain.myungsimbogam.Myungsimbogam;


@PrimaryRepositoryAnnoInterface
public interface MainRepository {
	
	//명심보감 데이터
	public Myungsimbogam findData(int weekOfYear);
	
}
