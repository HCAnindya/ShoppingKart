package com.shopping.kart.users.service;

import java.util.ArrayList;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.shopping.kart.users.entity.UserEntity;
import com.shopping.kart.users.entity.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository repository;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public com.shopping.kart.users.model.User createUser(com.shopping.kart.users.model.User user) {
		
		ModelMapper modelMapper = new ModelMapper();
		UserEntity entity = modelMapper.map(user, UserEntity.class);
		entity.setUserId(UUID.randomUUID().toString());
		entity.setEncryptedPassword(passwordEncoder.encode(user.getPassword()));
		repository.save(entity);
		return null;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity entity = repository.findByEmail(username);
		if (null == entity) throw new UsernameNotFoundException(username);
		return new User(entity.getEmail(), entity.getEncryptedPassword(), true, true, true, true, new ArrayList<>()		);
	}

	@Override
	public com.shopping.kart.users.model.User loadUserByUserName(String email) {
		UserEntity entity = repository.findByEmail(email);
		if (null == entity) throw new UsernameNotFoundException(email);
		return new ModelMapper().map(entity, com.shopping.kart.users.model.User.class);
	
	}

}
