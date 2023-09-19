package com.example.qwiqqer.verificationsservice.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class UserExceptionResponse implements Serializable {
	private int status;
	private String message;
	private LocalDateTime timestamp;
}
