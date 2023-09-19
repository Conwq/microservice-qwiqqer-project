package com.example.qwiqqer.verificationsservice.exception;

public class UserAlreadyExistException extends RuntimeException {

	public UserAlreadyExistException(String message){
		super(message);
	}
}
