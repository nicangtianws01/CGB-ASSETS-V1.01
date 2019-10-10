package com.cy.common.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cy.common.web.TimeAccessInterceptor;

@Configuration
public class SpringWebConfig implements WebMvcConfigurer{//web.xml
	@Bean
	public FilterRegistrationBean<DelegatingFilterProxy> newFilterRegistrationBean() {
		FilterRegistrationBean<DelegatingFilterProxy> fBean = new FilterRegistrationBean<>();
		DelegatingFilterProxy filter = new DelegatingFilterProxy("shiroFilterFactory");
		fBean.setFilter(filter);
		fBean.addUrlPatterns("/*");
		return fBean;
	}
	//配置spring mvc 拦截器
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	 registry.addInterceptor(new TimeAccessInterceptor())
			 .addPathPatterns("/user/doLogin");
	}
}
