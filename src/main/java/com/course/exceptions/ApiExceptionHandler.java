package com.course.exceptions;

public class ApiExceptionHandler extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Message;

	public ApiExceptionHandler() {
		super();

	}

	public ApiExceptionHandler(String message) {
		super(message);

	}

	public String getMessage() {
		return this.Message;
	}

}
