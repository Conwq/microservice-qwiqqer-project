package com.example.qwiqqer.usersservice.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
	private int status;
	private String message;
	private LocalDateTime timestamp;
}
