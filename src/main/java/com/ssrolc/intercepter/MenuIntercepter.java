package com.ssrolc.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class MenuIntercepter extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		String currentUrl = request.getRequestURI();
		
		AntPathMatcher ant = new AntPathMatcher();
		
		if(ant.match("/ssrolcmanager/disclosure*/**",currentUrl)){
			request.setAttribute("currentMenu","disclosure");
			
			return true;
		}else if(ant.match("/ssrolcmanager/franchise*/**",currentUrl) || ant.match("/ssrolcfront/franchise/search*/**",currentUrl)){
			request.setAttribute("currentMenu","franchise");
			
			return true;
		}else if(ant.match("/ssrolcfront/franchise/faq*/**",currentUrl)){
			request.setAttribute("currentMenu","franchiseFaq");
				
			return true;
		}else if(ant.match("/ssrolcmanager/center*/**",currentUrl) || ant.match("/ssrolcfront/centersearch/**",currentUrl)){
			request.setAttribute("currentMenu","center");
			
			return true;
		}else if(ant.match("/ssrolcmanager/board*/center/**",currentUrl) || ant.match("/ssrolcfront/board*/center/**",currentUrl)){
			request.setAttribute("currentMenu","boardCenter");
			
			return true;
		}else if(ant.match("/ssrolcmanager/board*/faq/**",currentUrl) || ant.match("/ssrolcfront/board*/faq/**",currentUrl)){
			request.setAttribute("currentMenu","boardFaq");
			
			return true;
		}else if(ant.match("/ssrolcmanager/board*/notice/**",currentUrl) || ant.match("/ssrolcfront/board*/notice/**",currentUrl)){
			request.setAttribute("currentMenu","boardNotice");
			
			return true;
		}else if(ant.match("/ssrolcmanager/board*/student/**",currentUrl) || ant.match("/ssrolcfront/board*/student/**",currentUrl)){
			request.setAttribute("currentMenu","boardStudent");
			
			return true;
		}else if(ant.match("/ssrolcmanager/popup*/**",currentUrl)){
			request.setAttribute("currentMenu","popup");
			
			return true;
		}else if(ant.match("/ssrolcmanager/prmedia*/**",currentUrl) || ant.match("/ssrolcfront/prmedia*/**",currentUrl) ){
			request.setAttribute("currentMenu","prmedia");
			
			return true;
		}else{
			request.setAttribute("currentMenu","main");
			return true;
		}
		
	}

}
