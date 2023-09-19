package com.example.qwiqqer.verificationsservice.repository;

import com.example.qwiqqer.verificationsservice.model.entity.UserEntity;

import java.util.Optional;

public interface VerificationRepository {
	Optional<UserEntity> checkingForExistUser(UserEntity userEntity);
}
