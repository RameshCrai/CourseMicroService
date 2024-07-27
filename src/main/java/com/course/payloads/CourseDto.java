package com.course.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CourseDto {
	private int courseId;
	private String code;
	private String title;
	private String author;
    private String studentId;
}
