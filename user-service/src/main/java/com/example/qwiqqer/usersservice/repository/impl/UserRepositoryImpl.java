package com.example.qwiqqer.usersservice.repository.impl;

import com.example.qwiqqer.usersservice.model.entity.UserEntity;
import com.example.qwiqqer.usersservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
	private final EntityManager entityManager;

	@Override
	public void saveUser(UserEntity userEntity) {
		entityManager.persist(userEntity);
	}
}
