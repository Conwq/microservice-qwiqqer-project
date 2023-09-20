package com.example.qwiqqer.usersservice.controller;

import com.example.qwiqqer.usersservice.model.dto.UserRequest;
import com.example.qwiqqer.usersservice.model.dto.UserResponse;
import com.example.qwiqqer.usersservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;

	@PostMapping
	public ResponseEntity<UserResponse> saveUser(@RequestBody UserRequest userRequest){
		userService.saveUser(userRequest);
		UserResponse userResponse = UserResponse.builder()
				.status(HttpStatus.CREATED.value())
				.message("User successfully registered.")
				.timestamp(LocalDateTime.now())
				.build();
		return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
	}
}
