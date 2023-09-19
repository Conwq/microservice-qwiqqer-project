package com.example.qwiqqer.usersservice.model.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VerificationRequest implements Serializable {
	private String email;
	private String username;
}
