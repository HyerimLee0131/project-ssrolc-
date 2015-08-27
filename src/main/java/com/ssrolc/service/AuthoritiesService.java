package com.ssrolc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssrolc.domain.common.User;
import com.ssrolc.domain.common.UserRole;
import com.ssrolc.repository.AuthoritiesRepository;

@Service
public class AuthoritiesService {
		
	@Autowired
	private AuthoritiesRepository authoritiesRepository;
	
	public User getUser(String userId){
		return authoritiesRepository.findUserByUserId(userId);
	}

	public List<UserRole> getUserRole(String userId) {
		return authoritiesRepository.findUserRolesByUserId(userId);
	}

	public boolean isUser(String userId,String userEncodeKey) {
		Map<String,Object> map = new HashMap<>();
		map.put("userId",userId);
		map.put("userEncodeKey",userEncodeKey);
		
		return authoritiesRepository.isUserByUserId(map);
	}
	
	public void setUserPassword(String userId,String userPassword,String newPass){
		Map<String,Object> map = new HashMap<>();
		map.put("userId",userId);
		map.put("userPassword",userPassword);
		map.put("newPass",newPass);
		
		authoritiesRepository.updateUserPassword(map);
	}
	
}
