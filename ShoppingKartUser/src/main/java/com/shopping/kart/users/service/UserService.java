package com.shopping.kart.users.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.shopping.kart.users.model.User;

public interface UserService extends UserDetailsService{
	
	User createUser(User user); 
	User loadUserByUserName(String userName);

}
