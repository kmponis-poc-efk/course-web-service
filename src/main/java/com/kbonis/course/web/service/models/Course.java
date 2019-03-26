package com.kbonis.course.web.service.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
public class Course implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String courseId;

	private String name;

	private String description;

	public Course() {}

	public Course(String courseId, String name, String description) {
		this.courseId = courseId;
		this.name = name;
		this.description = description;
	}

	public Course(String name, String description) {
		this.name = name;
		this.description = description;
	}
}