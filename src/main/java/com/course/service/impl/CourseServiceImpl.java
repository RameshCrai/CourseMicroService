package com.course.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.entity.Course;
import com.course.payloads.CourseDto;
import com.course.repository.CourseRepository;
import com.course.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {
	@Autowired
	private CourseRepository courseRepo;

	@Autowired
	private ModelMapper modelMapper;



	@Override
	public CourseDto createCourse(Course course) {
		Course courseObj = this.courseRepo.save(course);
		CourseDto courseData = modelMapper.map(courseObj, CourseDto.class);

		return courseData;
	}

	@Override
	public CourseDto getCourseByID(int id) {
		CourseDto courseObj = this.modelMapper.map(this.courseRepo.findById(id), CourseDto.class);

		return courseObj;
	}


}
