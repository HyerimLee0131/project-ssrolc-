package com.ssrolc.auth;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.google.common.base.Strings;
import com.ssrolc.domain.common.User;
import com.ssrolc.service.AuthoritiesService;




/**
 * 인증성공했을때 리다이렉트 시켜줌 요청했던 returl주소로
 * @author Administrator
 *
 */
@Component
public class MyCustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	private final static String DEFAULT_SSROMANAGER_INDEX_URL = "/ssrolcmanager/main";
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		addAuthCookie(response, authentication);
		String retUrl = request.getParameter("returl");
		if(Strings.isNullOrEmpty(retUrl)){
			response.sendRedirect(request.getContextPath()+DEFAULT_SSROMANAGER_INDEX_URL);
			return;
		}
		
		response.sendRedirect(retUrl);
	}
	
	
	private void addAuthCookie(HttpServletResponse response,Authentication authentication){
		User user = (User)authentication.getPrincipal();
		
		try {
			Cookie cookie = new Cookie("SSROLC_ID",URLEncoder.encode(user.getUserId(),"utf-8"));
			cookie.setPath("/");
			response.addCookie(cookie);
			
			Cookie cookie1 = new Cookie("SSROLC_KEY",URLEncoder.encode(user.getUserEncodeKey(),"utf-8"));
			cookie1.setPath("/");
			response.addCookie(cookie1);
			
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
}
