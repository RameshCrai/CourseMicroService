package com.course.exceptions;

public class CourseNotFoundException extends RuntimeException{
	
	public CourseNotFoundException(String mess) {
		super(mess);
	}

}
