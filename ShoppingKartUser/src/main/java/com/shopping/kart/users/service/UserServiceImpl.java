package com.shopping.kart.users.service;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.shopping.kart.users.entity.UserEntity;
import com.shopping.kart.users.entity.repository.UserRepository;
import com.shopping.kart.users.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository repository;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public User createUser(User user) {
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserEntity entity = modelMapper.map(user, UserEntity.class);
		entity.setUserId(UUID.randomUUID().toString());
		entity.setEncryptedPassword(passwordEncoder.encode(user.getPassword()));
		repository.save(entity);
		return null;
	}

}
