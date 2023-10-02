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

	@GetMapping
	public ResponseEntity<UserResponse> activateUser(@RequestParam("access_code") String code) {
		if (!userService.activateUser(code)){
			return buildResponse(HttpStatus.BAD_REQUEST);
		}
		return buildResponse(HttpStatus.OK);
	}

	private ResponseEntity<UserResponse> buildResponse(HttpStatus status) {
		if (status == HttpStatus.BAD_REQUEST) {
			return new ResponseEntity<>(UserResponse.builder()
					.status(HttpStatus.BAD_REQUEST.value())
					.message("Incorrect data.")
					.timestamp(LocalDateTime.now())
					.build(), HttpStatus.BAD_REQUEST);
		}
		else if (status == HttpStatus.CREATED) {
			return new ResponseEntity<>(UserResponse.builder()
					.status(HttpStatus.CREATED.value())
					.message("User successfully registered.")
					.timestamp(LocalDateTime.now())
					.build(), HttpStatus.CREATED);
		}
		else if (status == HttpStatus.OK) {
			return new ResponseEntity<>(UserResponse.builder()
					.status(HttpStatus.OK.value())
					.message("User successfully activated.")
					.timestamp(LocalDateTime.now())
					.build(), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(UserResponse.builder()
					.status(HttpStatus.NOT_FOUND.value())
					.message("Unknown error.")
					.timestamp(LocalDateTime.now())
					.build(), HttpStatus.NOT_FOUND);
		}
	}
}
