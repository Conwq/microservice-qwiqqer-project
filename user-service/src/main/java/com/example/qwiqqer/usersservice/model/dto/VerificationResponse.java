package com.example.qwiqqer.usersservice.model.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VerificationResponse implements Serializable {
	private boolean isExist;
}
