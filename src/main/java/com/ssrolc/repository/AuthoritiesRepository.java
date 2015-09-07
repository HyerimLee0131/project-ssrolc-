package com.ssrolc.repository;

import java.util.List;
import java.util.Map;

import com.ssrolc.domain.common.User;
import com.ssrolc.domain.common.UserRole;


@PrimaryRepositoryAnnoInterface
public interface AuthoritiesRepository {

	public User findUserByUserId(String userId);

	public List<UserRole> findUserRolesByUserId(String userId);

	public boolean isUserByUserId(Map<String, Object> map);

	public void updateUserPassword(Map<String, Object> map);
	
	public void updateUserEncodeKey(Map<String, Object> map);
}
