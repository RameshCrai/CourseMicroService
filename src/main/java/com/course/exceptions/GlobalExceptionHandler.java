package com.course.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.course.payloads.ApiResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponseDto> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {

		String message = "User not Exit";
		ApiResponseDto ApiResponseDto = new ApiResponseDto(message, false);
		return new ResponseEntity<ApiResponseDto>(ApiResponseDto, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

		Map<String, String> response = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String defaultMessage = error.getDefaultMessage();

			response.put(fieldName, defaultMessage);

		});
		;

		return new ResponseEntity<Map<String, String>>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ClassNotFoundException.class)
	public ResponseEntity<ApiResponseDto> handleClassNotFoundException(ClassNotFoundException ex) {
		String message = ex.getMessage();
		ApiResponseDto apiResponseDto = new ApiResponseDto(message, false);
		return new ResponseEntity<>(apiResponseDto, HttpStatus.BAD_REQUEST);
	}
}
