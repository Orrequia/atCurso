package com.fot.atCurso.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.fot.atCurso.component.mapper.course.CourseMapper;
import com.fot.atCurso.component.mapper.questionary.QuestionaryMapper;
import com.fot.atCurso.component.mapper.user.UserMapper;
import com.fot.atCurso.dto.course.CourseDTO;
import com.fot.atCurso.dto.course.CoursePostDTO;
import com.fot.atCurso.dto.questionary.QuestionaryDTO;
import com.fot.atCurso.dto.user.UserDTO;
import com.fot.atCurso.exceptions.IdValueCannotBeReceivedException;
import com.fot.atCurso.exceptions.NotFoundException;
import com.fot.atCurso.exceptions.ParametersNotAllowedException;
import com.fot.atCurso.exceptions.UniqueValueViolationException;
import com.fot.atCurso.model.Course;
import com.fot.atCurso.model.Questionary;
import com.fot.atCurso.model.User;
import com.fot.atCurso.service.course.CourseService;

@Controller
public class CourseController {

	private static final Integer maxSize = new Integer(10);
	
	@Autowired
	CourseService courseService;
	
	@Autowired
	CourseMapper courseMapper;
	
	@Autowired
	QuestionaryMapper questionaryMapper;
	
	@Autowired
	UserMapper userMapper;
	
	@GetMapping
	public Set<CourseDTO> findAll(@RequestParam(defaultValue = "0", required= false ) Integer page, 
			 	@RequestParam(defaultValue = "10", required= false ) Integer size) throws ParametersNotAllowedException {
		if(page < 0 || size <= 0 || size > maxSize)
			throw new ParametersNotAllowedException("Los parámetros introducidos contienen valores no permitidos, page mayor o igual a 0 y size entre 1 y " + maxSize + " incluídos");
		final Set<Course> courses = courseService.findAll(PageRequest.of(page, size));
		return courseMapper.modelToDto(courses);
	}
	
	@PostMapping
	public CourseDTO create(@RequestBody CoursePostDTO dto) throws IdValueCannotBeReceivedException, UniqueValueViolationException {
		if(dto.getIdCourse() != null) 
			throw new IdValueCannotBeReceivedException("El idCourse no se puede recibir");
		final Course course = courseMapper.dtoToModel(dto);
		final Course createUser = courseService.create(course);
		return courseMapper.modelToDto(createUser);
	}
	
	@GetMapping("/{idCourse}/user")
	public Set<UserDTO> findCourseUser(@RequestParam(defaultValue = "0", required= false ) Integer page, 
			 @RequestParam(defaultValue = "10", required = false ) Integer size,
			 @PathVariable("idCourse") Integer idCourse) throws ParametersNotAllowedException, NotFoundException {
		if(page < 0 || size <= 0 || size > maxSize)
			throw new ParametersNotAllowedException("Los parámetros introducidos contienen valores no permitidos, page mayor o igual a 0 y size entre 1 y " + maxSize + " incluídos");
		final Set<User> users = courseService.findCourseUsers(PageRequest.of(page, size), idCourse);
		return userMapper.modelToDto(users);
	}
	
	@GetMapping("/{idCourse}/questionary")
	public Set<QuestionaryDTO> findCourseQuestionaries(@RequestParam(defaultValue = "0", required= false ) Integer page, 
			 @RequestParam(defaultValue = "10", required = false) Integer size,
			 @PathVariable("idCourse") Integer idCourse) throws ParametersNotAllowedException, NotFoundException {
		if(page < 0 || size <= 0 || size > maxSize)
			throw new ParametersNotAllowedException("Los parámetros introducidos contienen valores no permitidos, page mayor o igual a 0 y size entre 1 y " + maxSize + " incluídos");
		final Set<Questionary> questionaries = courseService.findCourseQuestionaries(PageRequest.of(page, size), idCourse);
		return questionaryMapper.modelToDto(questionaries);
	}
}
