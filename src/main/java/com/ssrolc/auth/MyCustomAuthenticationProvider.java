package com.ssrolc.auth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.google.common.base.Strings;
import com.ssrolc.domain.common.User;
import com.ssrolc.domain.common.UserRole;
import com.ssrolc.service.AuthoritiesService;

/**
 * @Compoent를 선언해줘야 스캔할때 스프링이 bean으로 등록해줌
 * @author Administrator
 *
 */
@Component
public class MyCustomAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		
		UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) authentication;
		User user =  authoritiesService.getUser(authToken.getName());
		if(user == null){
			throw new UsernameNotFoundException(authToken.getName());
		}
		
		if(!matchPassword(user.getUserEncodeKey(),authToken.getCredentials().toString())){
			throw new BadCredentialsException("비밀키가 맞지않습니다.");
		}
		
		List<GrantedAuthority> authorities = getAuthorities(user.getUserId());
		return new UsernamePasswordAuthenticationToken(new User(user.getUserId(),user.getUserPassword(),user.getUserName()
				,user.getUserNick(),user.getUserEncodeKey(),user.getUserRegDate(),user.getUserLeaveDate(),user.getUserEnable()),null,authorities);
	}

	private List<GrantedAuthority> getAuthorities(String userId) {
		List<UserRole> perms = authoritiesService.getUserRole(userId);
		if (perms == null)
			return Collections.emptyList();

		List<GrantedAuthority> authorities = new ArrayList<>(perms.size());
		for (UserRole perm : perms) {
			authorities.add(new SimpleGrantedAuthority(perm.getAuthority()));
		}
		return authorities;
	}
	
	private boolean matchPassword(String memberPassword,String credentials) {
		if(Strings.isNullOrEmpty(memberPassword) || Strings.isNullOrEmpty(credentials) ){
			return false;
		}else{
			return memberPassword.equals(credentials);
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
