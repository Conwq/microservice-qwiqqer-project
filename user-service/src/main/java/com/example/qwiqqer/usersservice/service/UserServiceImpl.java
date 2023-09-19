package com.example.qwiqqer.usersservice.service;

import com.example.qwiqqer.usersservice.model.dto.UserRequest;
import com.example.qwiqqer.usersservice.model.entity.UserEntity;
import com.example.qwiqqer.usersservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	private final UserRepository userRepository;

	@Override
	@Transactional
	public void saveUser(UserRequest userRequest) {

		//TODO Добавить проверку при использовании другого сервиса для проверки существования пользователя, если сущ - Exception

		UserEntity userEntity = UserEntity.builder()
				.email(userRequest.getEmail())
				.username(userRequest.getUsername())
				.password(userRequest.getPassword())
				.build();

		userRepository.saveUser(userEntity);
	}
}
