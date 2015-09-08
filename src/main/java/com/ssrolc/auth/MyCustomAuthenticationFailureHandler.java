package com.ssrolc.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.google.common.base.Strings;

/**
 * 실패하면 로그인 폼으로 다시돌아가기
 * @author Administrator
 *
 */
@Component
public class MyCustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(MyCustomAuthenticationFailureHandler.class);
	
	private String loginFormPath;
		
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		
		LOGGER.debug("============================================="+loginFormPath);
		
		if(Strings.isNullOrEmpty(loginFormPath)){
			setLoginFormPath("/ssrolcmanager/login?error=true");
		}
		
		request.getRequestDispatcher(loginFormPath).forward(request,response);
		
	}

	public void setLoginFormPath(String loginFormPath) {
		this.loginFormPath = loginFormPath;
	}
	
}
