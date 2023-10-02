package com.example.qwiqqer.usersservice.repository.impl;

import com.example.qwiqqer.usersservice.model.entity.UserEntity;
import com.example.qwiqqer.usersservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
	private final EntityManager entityManager;

	@Override
	public void saveUser(UserEntity userEntity) {
		entityManager.persist(userEntity);
	}

	@Override
	public Optional<UserEntity> findUserByPersonalCode(String code) {
		TypedQuery<UserEntity> query = entityManager
				.createQuery("SELECT u FROM UserEntity u WHERE u.personalCode = :code", UserEntity.class);
		query.setParameter("code", code);
		return query.getResultList().stream().findAny();
	}
}
