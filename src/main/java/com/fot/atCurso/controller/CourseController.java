package com.fot.atCurso.controller;

import java.util.List;
import java.util.Optional;

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

import com.fot.atCurso.component.mapper.course.CourseMapper;
import com.fot.atCurso.dto.course.CourseDTO;
import com.fot.atCurso.exception.IdValueCannotBeReceivedException;
import com.fot.atCurso.exception.NotFoundException;
import com.fot.atCurso.exception.UnequalObjectsException;
import com.fot.atCurso.exception.ParametersNotAllowedException;
import com.fot.atCurso.model.Course;
import com.fot.atCurso.service.course.CourseService;

@RestController
@RequestMapping(value= "/course")
public class CourseController {
	
	@Autowired
	CourseService courseService;
	
	@Autowired
	CourseMapper courseMapper;
		
	@GetMapping
	public List<CourseDTO> findAll(@RequestParam(defaultValue = "0", required= false ) Integer page, 
			 	@RequestParam(defaultValue = "10", required= false ) Integer size) throws ParametersNotAllowedException {
		final List<Course> courses = courseService.findAll(PageRequest.of(page, size));
		return courseMapper.modelToDto(courses);
	}
	
	@GetMapping("/{idCourse}")
	public CourseDTO findById(@PathVariable("idCourse") Integer idCourse) throws NotFoundException {
		final Optional<Course> course = courseService.findById(idCourse);
		if (course.isPresent()) return courseMapper.modelToDto(course.get());
		throw new NotFoundException("El curso no existe");
	}
	
	@PostMapping
	public CourseDTO create(@RequestBody CourseDTO dto) throws IdValueCannotBeReceivedException, NotFoundException {
		if(dto.getIdCourse() != null) 
			throw new IdValueCannotBeReceivedException("El idCourse no se puede recibir");
		final Course course = courseMapper.dtoToModel(dto);
		final Course createCourse = courseService.create(course);
		return courseMapper.modelToDto(createCourse);
	}
	
	@PutMapping("/{idCourse}")
	public void update(@PathVariable("idCourse") Integer id, @RequestBody CourseDTO dto) throws IdValueCannotBeReceivedException, NotFoundException {
		if(dto.getIdCourse() != null) 
			throw new IdValueCannotBeReceivedException("El idCourse no se puede recibir en el body");
		final Optional<Course> course = courseService.findById(id);
		course.orElseThrow(() -> new NotFoundException("El curso no existe"));
		courseService.setValues(course.get(), courseMapper.dtoToModel(dto));
		courseService.update(course.get());
	}
	
	@DeleteMapping("/{idCourse}")
	public void delete(@PathVariable("idCourse") Integer id, @RequestBody CourseDTO dto) throws NotFoundException, UnequalObjectsException {
		final Optional<Course> course = courseService.findById(id);
		course.orElseThrow(() -> new NotFoundException("El usuario no existe"));
		if(!courseService.isEqual(courseMapper.dtoToModel(dto), course.get())) 
			throw new UnequalObjectsException("El usuario recibido no coincide con el almacenado");
		courseService.delete(course.get());
	}
}
