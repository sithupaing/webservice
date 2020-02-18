package com.code2.webservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code2.webservice.dto.CourseDto;
import com.code2.webservice.entity.Course;
import com.code2.webservice.repository.CourseRepository;

import javassist.NotFoundException;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepository courseRepository;

	@Override
	public CourseDto saveCourse(CourseDto courseDto) {

		Course course = new Course();
		BeanUtils.copyProperties(courseDto, course);

		Course savedCourse = courseRepository.save(course);
		CourseDto savedCourseDto = new CourseDto();
		BeanUtils.copyProperties(savedCourse, savedCourseDto);

		return savedCourseDto;
	}

	@Override
	public List<CourseDto> getCourses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CourseDto getCourseById(Long id) throws NotFoundException {
		Optional<Course> optional = courseRepository.findById(id);// 123
		if(optional.isPresent()) {
			CourseDto courseDto = new CourseDto();
			BeanUtils.copyProperties(optional.get(), courseDto);
			return courseDto;
		}
			
		throw new NotFoundException("Course not found");

	}

	@Override
	public void deleteCourse(Long id) {
		courseRepository.deleteById(id);
	}

	@Override
	public CourseDto updateCourse(CourseDto courseDto) {

		Course course = courseRepository.getOne(courseDto.getId());
		BeanUtils.copyProperties(courseDto, course);
		Course savedCourse = courseRepository.save(course);
		CourseDto savedCourseDto = new CourseDto();
		BeanUtils.copyProperties(savedCourse, savedCourseDto);
		return savedCourseDto;

	}

}
