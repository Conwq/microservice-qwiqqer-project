package com.example.qwiqqer.usersservice.repository;

import com.example.qwiqqer.usersservice.model.entity.UserEntity;

public interface UserRepository {
	void saveUser(UserEntity userEntity);
}
