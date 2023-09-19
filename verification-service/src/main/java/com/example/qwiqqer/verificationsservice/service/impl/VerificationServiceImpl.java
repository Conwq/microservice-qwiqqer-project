package com.example.qwiqqer.verificationsservice.service.impl;

import com.example.qwiqqer.verificationsservice.model.dto.VerificationRequest;
import com.example.qwiqqer.verificationsservice.model.dto.VerificationResponse;
import com.example.qwiqqer.verificationsservice.model.entity.UserEntity;
import com.example.qwiqqer.verificationsservice.repository.VerificationRepository;
import com.example.qwiqqer.verificationsservice.service.VerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VerificationServiceImpl implements VerificationService {
	private final VerificationRepository verificationRepository;

	@Override
	@Transactional(readOnly = true)
	public VerificationResponse checkingForExistUser(VerificationRequest verificationRequest){
		UserEntity userEntity = UserEntity.builder()
				.email(verificationRequest.getEmail())
				.username(verificationRequest.getUsername())
				.build();

//		if (isExistUser){
//			throw new UserAlreadyExistException("Current email or username already exist");
//		}
		return VerificationResponse.builder()
				.isExist(verificationRepository.checkingForExistUser(userEntity).isPresent())
				.build();
	}
}