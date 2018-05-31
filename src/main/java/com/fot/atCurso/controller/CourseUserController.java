package com.fot.atCurso.controller;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fot.atCurso.component.mapper.user.UserMapper;
import com.fot.atCurso.dto.user.UserDTO;
import com.fot.atCurso.exceptions.NotFoundException;
import com.fot.atCurso.exceptions.ParametersNotAllowedException;
import com.fot.atCurso.model.Course;
import com.fot.atCurso.model.User;
import com.fot.atCurso.service.course.CourseService;

@RequestMapping(value="course/{idCourse}/user")
public class CourseUserController {
	
	@Autowired
	CourseService courseService;
	
	@Autowired
	UserMapper userMapper;
	
	@GetMapping
	public Set<UserDTO> findAll(@RequestParam(defaultValue = "0", required= false ) Integer page, 
							 @RequestParam(defaultValue = "10", required= false ) Integer size,
							 @PathVariable("idCourse") Integer idCourse) throws ParametersNotAllowedException, NotFoundException {
		final Optional<Course> course = courseService.findById(idCourse);
		course.orElseThrow(() -> new NotFoundException("El curso no existe"));
		final Set<User> users = new PageImpl<User>(course.get().getUser(), PageRequest.of(page, size), course.get().getUser().size())
				.stream().collect(Collectors.toSet());
		return userMapper.modelToDto(users);
	}
	
	@GetMapping("/{idUser}")
	public UserDTO findById(@PathVariable("idCourse") Integer idCourse,
			 @PathVariable("idUser") Integer idUser) throws NotFoundException {
		final Optional<Course> course = courseService.findById(idCourse);
		course.orElseThrow(() -> new NotFoundException("El curso no existe"));
		final Optional<User> user = courseService.searchUser(course.get(), idUser);
		user.orElseThrow(() -> new NotFoundException("Este usuario no pertenece a este curso"));
		return userMapper.modelToDto(user.get());
	}
}
