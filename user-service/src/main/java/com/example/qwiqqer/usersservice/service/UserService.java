package com.example.qwiqqer.usersservice.service;

import com.example.qwiqqer.usersservice.model.dto.UserRequest;

public interface UserService {
	void saveUser(UserRequest userRequest);
}
