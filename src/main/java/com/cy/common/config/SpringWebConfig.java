package com.cy.common.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

@Configuration
public class SpringWebConfig {
	@Bean
	public FilterRegistrationBean<DelegatingFilterProxy> newFilterRegistrationBean() {
		FilterRegistrationBean<DelegatingFilterProxy> fBean = new FilterRegistrationBean<>();
		DelegatingFilterProxy filter = new DelegatingFilterProxy("shiroFilterFactory");
		fBean.setFilter(filter);
		fBean.addUrlPatterns("/*");
		return fBean;
	}
}
