package com.example.qwiqqer.verificationsservice.controller;

import com.example.qwiqqer.verificationsservice.model.dto.VerificationRequest;
import com.example.qwiqqer.verificationsservice.model.dto.VerificationResponse;
import com.example.qwiqqer.verificationsservice.service.VerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/verifications")
@RequiredArgsConstructor
public class VerificationController {
	private final VerificationService verificationService;

	@PostMapping
	public ResponseEntity<VerificationResponse> checkingForExistUser(@RequestBody VerificationRequest verificationRequest){
		return new ResponseEntity<>(verificationService.checkingForExistUser(verificationRequest),
				HttpStatus.OK);
	}

//	@ExceptionHandler(UserAlreadyExistException.class)
//	public ResponseEntity<UserExceptionResponse> userExistHandler(UserAlreadyExistException exc){
//		UserExceptionResponse exceptionResponse = UserExceptionResponse.builder()
//				.status(HttpStatus.CONFLICT.value())
//				.message(exc.getMessage())
//				.timestamp(LocalDateTime.now())
//				.build();
//		return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
//	}
}