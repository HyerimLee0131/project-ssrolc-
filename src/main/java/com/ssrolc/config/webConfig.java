package com.ssrolc.config;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mobile.device.DeviceHandlerMethodArgumentResolver;
import org.springframework.mobile.device.DeviceResolverHandlerInterceptor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.filter.HttpPutFormContentFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import com.ssrolc.intercepter.MenuIntercepter;

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
	
	@Bean
	public DeviceResolverHandlerInterceptor 
	        deviceResolverHandlerInterceptor() {
	    return new DeviceResolverHandlerInterceptor();
	}

	@Bean
	public DeviceHandlerMethodArgumentResolver 
	        deviceHandlerMethodArgumentResolver() {
	    return new DeviceHandlerMethodArgumentResolver();
	}

	
	@Bean
	public MenuIntercepter menuIntercepter(){
		return new MenuIntercepter();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(menuIntercepter()).addPathPatterns("/**").excludePathPatterns("/ssrolcmanager","/ssrolcmanager/login");
		registry.addInterceptor(deviceResolverHandlerInterceptor());
	}
	
	@Override
	public void addArgumentResolvers(
	        List<HandlerMethodArgumentResolver> argumentResolvers) {
	    argumentResolvers.add(deviceHandlerMethodArgumentResolver());
	}
	
	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
	    TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
	    tomcat.addAdditionalTomcatConnectors(createSslConnector());
	    return tomcat;
	}

	private Connector createSslConnector() {
	    Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
	    Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
	    try {
	        File keystore = new ClassPathResource("devspring.keystore").getFile();
	        File truststore = new ClassPathResource("devspring.keystore").getFile();
	        connector.setScheme("https");
	        connector.setSecure(true);
	        connector.setPort(8443);
	        protocol.setSSLEnabled(true);
	        protocol.setKeystoreFile(keystore.getAbsolutePath());
	        protocol.setKeystorePass("devspring");
	        protocol.setTruststoreFile(truststore.getAbsolutePath());
	        protocol.setTruststorePass("devspring");
	        protocol.setKeyAlias("tomcat");
	        return connector;
	    }
	    catch (IOException ex) {
	        throw new IllegalStateException("can't access keystore: [" + "keystore"
	                + "] or truststore: [" + "keystore" + "]", ex);
	    }
	}
	
	@Bean
	public BCryptPasswordEncoder setBCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}
}
