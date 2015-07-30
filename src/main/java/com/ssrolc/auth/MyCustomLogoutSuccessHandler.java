package com.ssrolc.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.ssrolc.service.AuthoritiesService;

@Component
public class MyCustomLogoutSuccessHandler implements LogoutSuccessHandler {
	
	@Autowired
	private AuthoritiesService authoritiesService;

	@Override
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		Cookie cookie = new Cookie("SSROLC_ID","");
		cookie.setPath("/");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		
		Cookie cookie1 = new Cookie("SSROLC_KEY","");
		cookie1.setPath("/");
		cookie1.setMaxAge(0);
		response.addCookie(cookie1);
		
		response.sendRedirect(request.getContextPath()+"/ssrolcmanager/login");
	}

}
