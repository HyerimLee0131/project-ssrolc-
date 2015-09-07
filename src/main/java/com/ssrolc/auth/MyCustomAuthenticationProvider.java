package com.ssrolc.auth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

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
	private static final Logger LOGGER = LoggerFactory.getLogger(MyCustomAuthenticationProvider.class);
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		
		UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) authentication;
		User user =  authoritiesService.getUser(authToken.getName());
		if(user == null){
			throw new UsernameNotFoundException(authToken.getName());
		}
		
		if(!passwordEncoder.matches(authToken.getCredentials().toString(),user.getUserPassword())){
			throw new BadCredentialsException("비밀번호가 맞지않습니다.");
		}
		
		List<GrantedAuthority> authorities = getAuthorities(user.getUserId());
		return new UsernamePasswordAuthenticationToken(new User(user.getUserId(),user.getUserPassword(),user.getUserName()
				,user.getUserNick(),user.getUserEncodeKey(),user.getUserRegDate(),user.getUserLeaveDate(),user.getUserEnable()),null,authorities);
	}

	private List<GrantedAuthority> getAuthorities(String userId) {
		List<UserRole> perms = authoritiesService.getUserRole(userId);
		
		if (perms == null){
			return Collections.emptyList();
		}
		
		List<GrantedAuthority> authorities = new ArrayList<>(perms.size());
		for (UserRole perm : perms) {
			authorities.add(new SimpleGrantedAuthority(perm.getAuthority()));
		}
		return authorities;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
