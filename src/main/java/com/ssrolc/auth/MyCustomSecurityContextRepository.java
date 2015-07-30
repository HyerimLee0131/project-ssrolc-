package com.ssrolc.auth;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Component;

import com.ssrolc.domain.common.User;
import com.ssrolc.domain.common.UserRole;
import com.ssrolc.service.AuthoritiesService;


@Component
public class MyCustomSecurityContextRepository implements SecurityContextRepository{
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
	@Override
	public SecurityContext loadContext(
			HttpRequestResponseHolder requestResponseHolder) {
		
		SecurityContext ctx = SecurityContextHolder.createEmptyContext();
		Map<String,Object> map = new HashMap<String, Object>();
		
		map = getAuthCookieValue(requestResponseHolder.getRequest());
		
		if(map != null && !map.isEmpty() && map.containsKey("SSROLC_ID") && map.containsKey("SSROLC_KEY")){
			String userId = map.get("SSROLC_ID").toString();
			String userEncodeKey = map.get("SSROLC_KEY").toString();
			
			boolean isUserFlag = authoritiesService.isUser(userId, userEncodeKey);
			
			if(isUserFlag){
				List<GrantedAuthority> authorities = new ArrayList<>();
				
				List<UserRole> memberAuthories = authoritiesService.getUserRole(userId);
				
				for (UserRole userRole : memberAuthories) {
					authorities.add(new SimpleGrantedAuthority(userRole.getAuthority()));
				}
				
				User user = authoritiesService.getUser(userId);
				user.setUserPassword("");
				user.setUserEncodeKey("");
				
				Authentication authentication = new UsernamePasswordAuthenticationToken(user,"",authorities);
				ctx.setAuthentication(authentication);
			}
		}
		
		return ctx;
	}

	@Override
	public boolean containsContext(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	@Override
	public void saveContext(SecurityContext context,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}
	
	private Map<String,Object> getAuthCookieValue(HttpServletRequest request){
		Cookie[] cookies =  request.getCookies();
		Map<String,Object> map = new HashMap<String,Object>();
		
		if(cookies == null){
			return null;
		}
		
		for (Cookie cookie : cookies) {
			if("SSROLC_ID".equals(cookie.getName())){
				try {
					map.put("SSROLC_ID",URLDecoder.decode(cookie.getValue(),"utf-8"));
				} catch (UnsupportedEncodingException e) {
					return null;
				}
			}else if("SSROLC_KEY".equals(cookie.getName())){
				try {
					map.put("SSROLC_KEY",URLDecoder.decode(cookie.getValue(),"utf-8"));
				} catch (UnsupportedEncodingException e) {
					return null;
				}
			}
		}
		
		return map;
	}
	
	
}
