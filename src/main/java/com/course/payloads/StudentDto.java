package com.course.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentDto {
	private int studentID;
	private String fullName;
	private String email;
	private String mobile;

}
