package com.example.qwiqqer.usersservice.service.impl;

import com.example.qwiqqer.usersservice.exception.UserAlreadyExistException;
import com.example.qwiqqer.usersservice.model.dto.UserRequest;
import com.example.qwiqqer.usersservice.model.dto.VerificationRequest;
import com.example.qwiqqer.usersservice.model.dto.VerificationResponse;
import com.example.qwiqqer.usersservice.model.entity.UserEntity;
import com.example.qwiqqer.usersservice.repository.UserRepository;
import com.example.qwiqqer.usersservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
	private final RestTemplate restTemplate;
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	@Transactional
	public void saveUser(UserRequest userRequest) {
		ResponseEntity<VerificationResponse> response = getUserVerificationResponse(userRequest);

		if (response.getBody().isExist()) {
			LOGGER.error("User with current email or username exist.");

			//TODO создать этот Exception и создать ExceptionHandler для этого Exception

			throw new UserAlreadyExistException("User with current email or username exist. " +
					"Please choose another data.");
		}

		UserEntity userEntity = mapToEntity(userRequest);
		userRepository.saveUser(userEntity);
	}

	private UserEntity mapToEntity(UserRequest userRequest){
		return UserEntity.builder()
				.email(userRequest.getEmail())
				.username(userRequest.getUsername())
				.password(userRequest.getPassword())
				.build();
	}

	private ResponseEntity<VerificationResponse> getUserVerificationResponse(UserRequest userRequest){
		VerificationRequest verificationRequest = VerificationRequest.builder()
				.email(userRequest.getEmail())
				.username(userRequest.getUsername())
				.build();

		return restTemplate.postForEntity("http://localhost:8888/api/v1/verifications",
						verificationRequest, VerificationResponse.class);
	}
}
