package com.code2.webservice.service;

import java.util.List;

import com.code2.webservice.dto.CourseDto;
import com.code2.webservice.dto.UserDto;

import javassist.NotFoundException;

public interface CourseService {
	
	public List<CourseDto> getCourses();
	public CourseDto saveCourse(CourseDto course);
	public CourseDto getCourseById(Long id)throws NotFoundException;
	public void deleteCourse(Long id);
	public CourseDto updateCourse(CourseDto course);

}
