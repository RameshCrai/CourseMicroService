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
import com.course.exceptions.UserNotFoundException;
import com.course.payloads.CourseDto;
import com.course.service.impl.CourseServiceImpl;

@RestController
public class CourseController {
	@Autowired
	private CourseServiceImpl serviceimpl;

	@PostMapping("/create-student")
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

//	public CompletableFuture<ResponseEntity<StudentDto>> createStudentObj(@RequestBody Student std){
//		return CompletableFuture.supplyAsync(()->{
//			StudentDto stdObj = this.serviceimpl.createStudent(std);
//			
//			return ResponseEntity.status(HttpStatus.OK).body(stdObj);
//		});
//	}

//	@GetMapping("/get-student")
//	public ResponseEntity<StudentDto> getStudent(@PathVariable("id") int id){
//		StudentDto studentObj = this.serviceimpl.getStudentByID(id);
//		return ResponseEntity.status(HttpStatus.OK).body(studentObj);
//	}

	@GetMapping("/get-student/{id}")
	public CompletableFuture<ResponseEntity<?>> getCourseData(@PathVariable("id") int id) {
		return CompletableFuture.supplyAsync(() -> {
			try {
				CourseDto courseObj = this.serviceimpl.getCourseByID(id);
				if (courseObj.getCourseId() == id) {
					return ResponseEntity.status(HttpStatus.OK).body(courseObj);
				} else {
					String emessage = "This data not Exit in Real";
					return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiExceptionHandler(emessage));
				}
			} catch (UserNotFoundException ex) {
				ex.printStackTrace();
				String emessage = "This data not Exit in Real";
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiExceptionHandler(emessage));
			}
		});
	}

}
