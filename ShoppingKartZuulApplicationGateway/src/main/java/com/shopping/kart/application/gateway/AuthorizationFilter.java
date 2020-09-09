package com.shopping.kart.application.gateway;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

public class AuthorizationFilter extends BasicAuthenticationFilter{

	private String authHeader;
	
	private String secretKey;
	
	private String authBearer;
		
	public AuthorizationFilter(AuthenticationManager authenticationManager, Environment env) {
		super(authenticationManager);
		this.authHeader = env.getProperty("auth.header");
		this.authBearer = env.getProperty("auth.bearer");
		this.secretKey = env.getProperty("jwt.secret");
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain chain)
			throws IOException, ServletException {
		String authenticationHeader = request.getHeader(authHeader);
		if(authenticationHeader == null || !authenticationHeader.startsWith(authBearer)) {
			chain.doFilter(request, response);
			return;
		}
		UsernamePasswordAuthenticationToken authenticate = getAuthentication(request);
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(authHeader).replace(authBearer, "");
		
		String userId = Jwts.parser()
				.setSigningKey(secretKey)
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
		
		if(userId == null) {
			return null;
		}
		System.out.println(userId);
		return new UsernamePasswordAuthenticationToken(userId, null, new ArrayList<>()	);
	}
	
}
