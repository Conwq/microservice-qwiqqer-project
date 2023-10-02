package com.example.qwiqqer.usersservice.controller;

import com.example.qwiqqer.usersservice.model.dto.UserRequest;
import com.example.qwiqqer.usersservice.model.dto.UserResponse;
import com.example.qwiqqer.usersservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;

	@PostMapping
	public ResponseEntity<UserResponse> saveUser(@RequestBody @Valid UserRequest userRequest,
												 BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return buildResponse(HttpStatus.BAD_REQUEST);
		}
		userService.saveUser(userRequest);
		return buildResponse(HttpStatus.CREATED);
	}

	private ResponseEntity<UserResponse> buildResponse(HttpStatus status) {
		if (status == HttpStatus.BAD_REQUEST) {
			return new ResponseEntity<>(UserResponse.builder()
					.status(HttpStatus.BAD_REQUEST.value())
					.message("Incorrect data.")
					.timestamp(LocalDateTime.now())
					.build(), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(UserResponse.builder()
				.status(HttpStatus.CREATED.value())
				.message("User successfully registered.")
				.timestamp(LocalDateTime.now())
				.build(), HttpStatus.CREATED);
	}

	@GetMapping("{/code}")
	public void activateUser(@PathVariable("code") String code){
		userService.activateUser(code);
	}
}
