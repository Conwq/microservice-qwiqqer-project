package com.example.qwiqqer.usersservice.repository;

import com.example.qwiqqer.usersservice.model.entity.UserEntity;

import java.util.Optional;

public interface UserRepository {
	void saveUser(UserEntity userEntity);
	Optional<UserEntity> findUserByPersonalCode(String code);
}
