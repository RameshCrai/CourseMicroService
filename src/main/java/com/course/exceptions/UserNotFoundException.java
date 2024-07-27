package com.course.exceptions;

public class UserNotFoundException extends RuntimeException{
	
	public UserNotFoundException(String mess) {
		super(mess);
	}

}
