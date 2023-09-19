package com.example.qwiqqer.verificationsservice.repository.impl;

import com.example.qwiqqer.verificationsservice.model.entity.UserEntity;
import com.example.qwiqqer.verificationsservice.repository.VerificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class VerificationRepositoryImpl implements VerificationRepository {
	private final EntityManager entityManager;

	@Override
	public Optional<UserEntity> checkingForExistUser(UserEntity userEntity){
		TypedQuery<UserEntity> userEntityOptional =
				entityManager.createQuery("FROM UserEntity u WHERE u.email = :email OR u.username = :username",
						UserEntity.class);
		userEntityOptional.setParameter("email", userEntity.getEmail());
		userEntityOptional.setParameter("username", userEntity.getUsername());
		return userEntityOptional.getResultList().stream().findAny();
	}
}
