package com.fot.atcurso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fot.atcurso.component.mapper.course.CourseMapper;
import com.fot.atcurso.dto.course.CourseDTO;
import com.fot.atcurso.exception.IdValueCannotBeReceivedException;
import com.fot.atcurso.exception.NotFoundException;
import com.fot.atcurso.exception.UnequalObjectsException;
import com.fot.atcurso.exception.IncorrectParametersException;
import com.fot.atcurso.model.Course;
import com.fot.atcurso.service.course.CourseService;

@RestController
@RequestMapping(value= "/course")
public class CourseController {
	
	@Autowired
	CourseService courseService;
	
	@Autowired
	CourseMapper courseMapper;
		
	@GetMapping
	public List<CourseDTO> findAll(@RequestParam(defaultValue = "0", required= false ) Integer page, 
			 	@RequestParam(defaultValue = "10", required= false ) Integer size,
			 	@RequestParam(defaultValue = "0", required=false) Integer user) throws IncorrectParametersException, NotFoundException {
		List<Course> courses;
		if(user != 0) courses = courseService.findByUser(user, PageRequest.of(page, size));
		else courses = courseService.findAll(PageRequest.of(page, size));
		return courseMapper.modelToDto(courses);
	}
	
	@GetMapping("/{idCourse}")
	public CourseDTO findById(@PathVariable("idCourse") Integer idCourse) throws NotFoundException {
		Course course = courseService.getAndCheck(idCourse);
		return courseMapper.modelToDto(course);
	}
	
	@PostMapping
	public CourseDTO create(@RequestBody CourseDTO dto) throws IdValueCannotBeReceivedException, NotFoundException {
		if(dto.getIdCourse() != null) 
			throw new IdValueCannotBeReceivedException("El idCourse no se puede recibir en el body");
		final Course course = courseMapper.dtoToModel(dto);
		final Course createCourse = courseService.create(course);
		return courseMapper.modelToDto(createCourse);
	}
	
	@PutMapping("/{idCourse}")
	public void update(@PathVariable("idCourse") Integer id, @RequestBody CourseDTO dto) throws IdValueCannotBeReceivedException, NotFoundException {
		if(dto.getIdCourse() != null) 
			throw new IdValueCannotBeReceivedException("El idCourse no se puede recibir en el body");
		Course course = courseService.getAndCheck(id);
		courseService.setValues(course, courseMapper.dtoToModel(dto));
		courseService.update(course);
	}
	
	@DeleteMapping("/{idCourse}")
	public void delete(@PathVariable("idCourse") Integer id, @RequestBody CourseDTO dto) throws NotFoundException, UnequalObjectsException {
		Course course = courseService.getAndCheck(id);
		if(!courseService.isEqual(courseMapper.dtoToModel(dto), course)) 
			throw new UnequalObjectsException("El usuario recibido no coincide con el almacenado");
		courseService.delete(course);
	}
}
