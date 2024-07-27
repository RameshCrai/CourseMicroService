package com.course.service;

import com.course.entity.Course;
import com.course.payloads.CourseDto;


public interface CourseService {
	
	CourseDto createCourse(Course course);
	
	CourseDto getCourseByID(int id);

}
