package com.example.qwiqqer.usersservice.util.mapper;

import com.example.qwiqqer.usersservice.model.dto.UserRequest;
import com.example.qwiqqer.usersservice.model.entity.UserEntity;
import com.example.qwiqqer.usersservice.util.Mapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends Mapper<UserEntity, UserRequest> {
	@Override
	public UserRequest mapToDto(UserEntity userEntity) {
		return UserRequest.builder()
				.email(userEntity.getEmail())
				.username(userEntity.getUsername())
				.password(userEntity.getPassword())
				.build();
	}

	@Override
	public UserEntity mapToEntity(UserRequest userRequest) {
		return UserEntity.builder()
				.email(userRequest.getEmail())
				.username(userRequest.getUsername())
				.password(userRequest.getPassword())
				.build();
	}
}
