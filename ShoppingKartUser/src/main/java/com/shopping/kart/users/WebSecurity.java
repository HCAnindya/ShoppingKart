package com.shopping.kart.users;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.beans.factory.annotation.Value;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{
	
	@Value("${gateway.ip}")
	private String ipaddressExpression;	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		//http.authorizeRequests().antMatchers("/users/status").denyAll();
		//http.authorizeRequests().antMatchers("/users/**").permitAll();
		System.out.println(ipaddressExpression);
		http.authorizeRequests().antMatchers("/**").hasIpAddress(ipaddressExpression);
	}
}
