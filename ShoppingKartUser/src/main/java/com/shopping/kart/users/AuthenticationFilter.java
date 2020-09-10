package com.shopping.kart.users;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopping.kart.users.model.LoginCredentails;
import com.shopping.kart.users.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private String secretKey;
	
	UserService userService;
	
	public AuthenticationFilter(UserService userService, 
			AuthenticationManager authenticationManager,
			Environment env) {
		this.userService = userService;
		super.setAuthenticationManager(authenticationManager);
		this.secretKey = env.getProperty("jwt.secret");
		
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		try {
			LoginCredentails cred = new ObjectMapper().readValue(request.getInputStream(), LoginCredentails.class);

			return getAuthenticationManager().authenticate(
					new UsernamePasswordAuthenticationToken(cred.getEmail(), cred.getPassword(), new ArrayList<>()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}

	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		System.out.println("Login Success");
		String userName = ((User)authResult.getPrincipal()).getUsername();	
		com.shopping.kart.users.model.User user = userService.loadUserByUserName(userName);
		System.out.println("Login Success" + user.getFirstName());
		
		String token = 	Jwts.builder()
				.setSubject(user.getUserId())
				.setExpiration(new Date(System.currentTimeMillis() + 86000L))
				.signWith(SignatureAlgorithm.HS512, secretKey)
				.compact();	
		response.addHeader("token", token);
		response.addHeader("userId", user.getUserId());	
	}
}
