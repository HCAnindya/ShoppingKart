package com.shopping.kart.users.entity.repository;

import org.springframework.data.repository.CrudRepository;

import com.shopping.kart.users.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

	UserEntity findByEmail(String email);
}
