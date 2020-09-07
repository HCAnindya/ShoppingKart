package com.shopping.kart.users.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.kart.users.model.User;
import com.shopping.kart.users.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService userService;

	@Autowired
	private Environment env;
	
	@GetMapping("/status")
	public String status(){
		return "UserController 	Working on port:" + env.getProperty("local.server.port");	
	}
	
	@PostMapping
	public String createUser(@Valid @RequestBody User user) {
		System.out.println(user.getFirstName());
		userService.createUser(user);
		return "User Created";
	}
}
