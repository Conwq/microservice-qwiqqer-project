package com.example.qwiqqer.usersservice.exception;

import com.example.qwiqqer.usersservice.model.dto.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class UserExceptionHandler {

	@ExceptionHandler(UserAlreadyExistException.class)
	public ResponseEntity<UserResponse> userExistException(UserAlreadyExistException exc){
		UserResponse userResponse = UserResponse.builder()
				.status(HttpStatus.BAD_REQUEST.value())
				.message(exc.getMessage())
				.timestamp(LocalDateTime.now())
				.build();
		return new ResponseEntity<>(userResponse, HttpStatus.BAD_REQUEST);
	}
}
