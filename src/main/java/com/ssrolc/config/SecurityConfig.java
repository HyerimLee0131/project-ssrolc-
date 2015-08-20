package com.ssrolc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.savedrequest.NullRequestCache;

import com.ssrolc.auth.MyCustomAuthenticationEntryPoint;
import com.ssrolc.auth.MyCustomAuthenticationFailureHandler;
import com.ssrolc.auth.MyCustomAuthenticationProvider;
import com.ssrolc.auth.MyCustomAuthenticationSuccessHandler;
import com.ssrolc.auth.MyCustomLogoutSuccessHandler;
import com.ssrolc.auth.MyCustomSecurityContextRepository;


/**
 * 시큐리티 설정 http://docs.spring.io/spring-security/site/docs/3.2.6.RELEASE/reference/htmlsingle/#jc-form 참고
 * @author Administrator
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
		
		@Autowired
		private MyCustomAuthenticationProvider myCustomAuthenticationProvider;
	
		@Autowired
		private MyCustomAuthenticationSuccessHandler myCustomAuthenticationSuccessHandler;
		
		@Autowired
		private MyCustomAuthenticationFailureHandler myCustomAuthenticationFailureHandler;
		
		@Autowired
		private MyCustomAuthenticationEntryPoint myCustomAuthenticationEntryPoint;
		
		@Autowired
		private MyCustomLogoutSuccessHandler myCustomLogoutSuccessHandler;
		
		@Autowired
		private MyCustomSecurityContextRepository myCustomSecurityContextRepository;
		
		@Override
		protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
			auth.authenticationProvider(myCustomAuthenticationProvider);
		}
	
		@Override
		public void configure(WebSecurity web) throws Exception {
			web.ignoring().antMatchers("/public/js/**","/public/css/**","/public/img/**","/favicon.ico");
		}
		
		@Override
		public void configure(HttpSecurity http) throws Exception {
				http
					.securityContext().securityContextRepository(myCustomSecurityContextRepository)
				.and()
					.csrf().disable()
					.authorizeRequests()
					.antMatchers("/ssrolcmanager/login","/ssrolcmanager/loginCheck","/ssrolcmanager").permitAll()
					.antMatchers("/ssrolcmanager/**").hasAuthority("SSROLCSUPERADMIN")
					.anyRequest().permitAll()
				.and()
					.formLogin()
						.usernameParameter("userId")
						.passwordParameter("userEncodeKey")
						.loginProcessingUrl("/ssrolcmanager/loginCheck")
						.successHandler(myCustomAuthenticationSuccessHandler)
						.failureHandler(myCustomAuthenticationFailureHandler)
						.permitAll()
				.and()
					.logout()
						.logoutUrl("/ssrolcmanager/logout")
						.logoutSuccessUrl("/ssrolcmanager/login")
						.logoutSuccessHandler(myCustomLogoutSuccessHandler)
						.permitAll()
				.and()
					.httpBasic()
				.and()
					.exceptionHandling()
						.authenticationEntryPoint(myCustomAuthenticationEntryPoint)
				.and()
					.requestCache().requestCache(new NullRequestCache())
				.and()
					.headers()
						.addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN));
				//나모 웹에디터때문에 위에 sameorigin으로 설정
		}
		
}
