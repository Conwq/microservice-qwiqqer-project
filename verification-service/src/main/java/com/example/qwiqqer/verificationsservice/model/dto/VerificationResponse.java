package com.example.qwiqqer.verificationsservice.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class VerificationResponse {
	private boolean isExist;
}
