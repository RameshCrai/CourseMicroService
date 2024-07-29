package com.course.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.course.entity.Course;
import com.course.exceptions.ApiExceptionHandler;
import com.course.exceptions.CourseNotFoundException;
import com.course.payloads.CourseDto;
import com.course.service.impl.CourseServiceImpl;

@RestController
public class CourseController {
	@Autowired
	private CourseServiceImpl serviceimpl;

	@PostMapping("/add-course")
	public ResponseEntity<?> saveCourse(@RequestBody Course course) throws ApiExceptionHandler {
		try {
			CourseDto courseObj = this.serviceimpl.createCourse(course);
			return ResponseEntity.status(HttpStatus.OK).body(courseObj);
		} catch (ApiExceptionHandler e) {
			e.printStackTrace();
			ApiExceptionHandler apiExceptionHandler = new ApiExceptionHandler("This does not exist");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiExceptionHandler);
		}
	}


	@GetMapping("/get-course/{id}")
	public CompletableFuture<ResponseEntity<?>> getCourse(@PathVariable("id") int id) {
		return CompletableFuture.supplyAsync(() -> {
			try {
				CourseDto courseObj = this.serviceimpl.getCourseByID(id);
				if (courseObj.getCourseId() == id) {
					return ResponseEntity.status(HttpStatus.OK).body(courseObj);
				} else {
					String emessage = "This data not Exit in Real";
					return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiExceptionHandler(emessage));
				}
			} catch (CourseNotFoundException ex) {
				ex.printStackTrace();
				String emessage = "This data not Exit in Real";
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiExceptionHandler(emessage));
			}
		});
	}

}
