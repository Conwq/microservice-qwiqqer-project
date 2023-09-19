package com.example.qwiqqer.verificationsservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerificationRequest implements Serializable {
	private String email;
	private String username;
}
