package com.code2.webservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code2.webservice.dto.CourseDto;
import com.code2.webservice.payload.request.CourseRequest;
import com.code2.webservice.payload.response.ApiResponse;
import com.code2.webservice.payload.response.CourseResponse;
import com.code2.webservice.service.CourseService;

import javassist.NotFoundException;

@RestController
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	private CourseService courseService;

	@PostMapping
	public CourseResponse createCourse(@RequestBody CourseRequest courseRequest) {
		
		

		CourseDto courseDto = new CourseDto();
		BeanUtils.copyProperties(courseRequest, courseDto);

		CourseDto savedCourseDto = courseService.saveCourse(courseDto);
		CourseResponse courseResponse = new CourseResponse();
		BeanUtils.copyProperties(savedCourseDto, courseResponse);

		return courseResponse;
	}

	@GetMapping
	public List<CourseResponse> getCourses() {
		
		List<CourseDto> courses = courseService.getCourses();
		List<CourseResponse> courseResponseList = new ArrayList<CourseResponse>();

		for (CourseDto course : courses) {
			CourseResponse courseResponse = new CourseResponse();
			BeanUtils.copyProperties(course, courseResponse);
			courseResponseList.add(courseResponse);
		}
		return courseResponseList;
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getCourse(@PathVariable("id") Long id) throws NotFoundException  {
		
//		try {
			CourseDto courseDto = courseService.getCourseById(id);
			CourseResponse courseResponse = new CourseResponse();
			BeanUtils.copyProperties(courseDto, courseResponse);
			ResponseEntity<CourseResponse> response =  new ResponseEntity<>(courseResponse,HttpStatus.CREATED);
			return response;
//		} catch (NotFoundException e) {
//			ApiResponse apiResponse = new ApiResponse(false, "User not found");
//			ResponseEntity<ApiResponse> response = new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
//			return response;
//		}
		
	}

	@PutMapping("/{id}")
	public CourseResponse updateCourse(@PathVariable("id") Long id, @RequestBody CourseRequest courseRequest) throws NotFoundException {
		CourseDto courseDto = new CourseDto();
		courseDto.setId(id);
		BeanUtils.copyProperties(courseRequest, courseDto);
		
		CourseDto updateCourse = courseService.updateCourse(courseDto);
		CourseResponse courseResponse = new CourseResponse();
		BeanUtils.copyProperties(updateCourse, courseResponse);
		return courseResponse;
	}

	@DeleteMapping("/{id}")
	public ApiResponse deleteCourse(@PathVariable("id") Long id) {
		courseService.deleteCourse(id);
		return new ApiResponse(true, "Successfully deleted");
	}

}
