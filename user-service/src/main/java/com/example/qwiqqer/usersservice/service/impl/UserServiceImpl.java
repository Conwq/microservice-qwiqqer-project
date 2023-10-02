package com.example.qwiqqer.usersservice.service.impl;

import com.example.qwiqqer.usersservice.exception.UserAlreadyExistException;
import com.example.qwiqqer.usersservice.model.dto.MessageRequest;
import com.example.qwiqqer.usersservice.model.dto.UserRequest;
import com.example.qwiqqer.usersservice.model.dto.VerificationRequest;
import com.example.qwiqqer.usersservice.model.dto.VerificationResponse;
import com.example.qwiqqer.usersservice.model.entity.UserEntity;
import com.example.qwiqqer.usersservice.repository.UserRepository;
import com.example.qwiqqer.usersservice.service.UserService;
import com.example.qwiqqer.usersservice.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	@Value("${qwiqqer.exchange.name}")
	private String exchange;
	@Value("${qwiqqer.routing.key.name}")
	private String routingKey;
	private final Mapper<UserEntity, UserRequest> mapper;
	private final UserRepository userRepository;
	private final RestTemplate restTemplate;
	private final RabbitTemplate rabbitTemplate;
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	@Transactional
	public void saveUser(UserRequest userRequest) {
		//Создаю и отправляю запрос на проверку существования пользователя на другой сервис
		ResponseEntity<VerificationResponse> response = getUserVerificationResponse(userRequest);
		if (response.getBody().isExist()) {
			LOGGER.error("User with current email or username exist.");
			throw new UserAlreadyExistException("User with current email or username exist.");
		}

		userRequest.setPassword(BCrypt.hashpw(userRequest.getPassword(), BCrypt.gensalt()));

		// Для отправки уведомлений на почту для подтверждения электронной почты.
		String personalCode = UUID.randomUUID().toString();
		UserEntity userEntity = mapper.mapToEntity(userRequest);
		userEntity.setActivated(false);
		userEntity.setPersonalCode(personalCode);

		MessageRequest messageRequest = new MessageRequest(userEntity.getUsername(), userEntity.getEmail(), personalCode);
		rabbitTemplate.convertAndSend(exchange, routingKey, messageRequest);

		userRepository.saveUser(userEntity);
	}

	private ResponseEntity<VerificationResponse> getUserVerificationResponse(UserRequest userRequest) {
		VerificationRequest verificationRequest = VerificationRequest.builder()
				.email(userRequest.getEmail())
				.username(userRequest.getUsername())
				.build();

		return restTemplate.postForEntity("http://localhost:8888/api/v1/verifications",
				verificationRequest, VerificationResponse.class);
	}

	@Transactional
	public boolean activateUser(String code){
		Optional<UserEntity> optionalUser = userRepository.findUserByPersonalCode(code);
		if (optionalUser.isEmpty()){
			LOGGER.warn("User not activated.");
			return false;
		}
		UserEntity userEntity = optionalUser.get();
		userEntity.setPersonalCode(null);
		userEntity.setActivated(true);
		return true;
	}
}
