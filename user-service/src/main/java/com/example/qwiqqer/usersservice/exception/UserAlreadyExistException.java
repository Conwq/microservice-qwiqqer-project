package com.example.qwiqqer.usersservice.exception;

public class UserAlreadyExistException extends RuntimeException{

	public UserAlreadyExistException(String message){
		super(message);
	}
}