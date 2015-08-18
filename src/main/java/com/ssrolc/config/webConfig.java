package com.ssrolc.config;

import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.filter.HttpPutFormContentFilter;

@Configuration
public class webConfig extends WebMvcAutoConfigurationAdapter {

	
	/**
	 * Message를 이용하기 위한 Bean 설정
	 * 
	 * @param messageSource
	 * @return
	 */
	@Bean
	public MessageSourceAccessor messageSourceAccesor(
			MessageSource messageSource) {
		return new MessageSourceAccessor(messageSource);
	}
	
	/**
	 * 필터 등록 Bean 설정
	 * 현재 : CharacterEncodingFilter , HiddenHttpMethodFilter , HttpPutFormContentFilter 등록
	 * @return
	 */
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();
		HttpPutFormContentFilter httpPutFormContentFilter = new HttpPutFormContentFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		registrationBean.setFilter(characterEncodingFilter);
		registrationBean.setFilter(hiddenHttpMethodFilter);
		registrationBean.setFilter(httpPutFormContentFilter);
		return registrationBean;
	}
	
}
