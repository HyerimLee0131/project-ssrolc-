package com.ssrolc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.ssrolc.domain.common.User;
import com.ssrolc.domain.common.UserRole;
import com.ssrolc.repository.AuthoritiesRepository;

@Service
public class AuthoritiesService {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
		
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
	
	public String setUserPassword(String userId,String oldPass,String newPass,String newPassConfirm){
		Map<String,Object> map = new HashMap<>();
		map.put("userId",userId);
		
		User user = authoritiesRepository.findUserByUserId(userId);
		
		String result = "";
		
		if(user == null){
			throw new RuntimeException();
		}else{
			if(!Strings.isNullOrEmpty(oldPass) && !Strings.isNullOrEmpty(newPass) && !Strings.isNullOrEmpty(newPassConfirm)){
				if(passwordEncoder.matches(oldPass,user.getUserPassword())){
					if(newPass.equals(newPassConfirm)){
						
						map.put("newPass",passwordEncoder.encode(newPass));
						
						authoritiesRepository.updateUserPassword(map);
											
						result = "비밀번호가 변경되었습니다.";
					}
				}else{
					result = "기존 비밀번호가 맞지 않습니다.";
				}
			}else{
				result = "필수 입력 값들을 입력바랍니다.";
			}
		}
		
		return result;
	}
	
	public void setUserEncodeKey(String userId,String encodeKey){
		Map<String,Object> map = new HashMap<>();
		map.put("userId",userId);
		map.put("encodeKey",encodeKey);
		
		authoritiesRepository.updateUserEncodeKey(map);
	}
	
}
