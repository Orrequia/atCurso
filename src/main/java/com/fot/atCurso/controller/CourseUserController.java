package com.fot.atCurso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fot.atCurso.component.mapper.user.UserMapper;
import com.fot.atCurso.dto.user.UserDTO;
import com.fot.atCurso.exception.NotFoundException;
import com.fot.atCurso.model.User;
import com.fot.atCurso.service.user.UserService;

@RestController
@RequestMapping(value="course/{idCourse}/user")
public class CourseUserController {
	
	private final UserService userService;
	private final UserMapper userMapper;

	@Autowired
	public CourseUserController(UserService userService, UserMapper userMapper) {
		this.userService = userService;
		this.userMapper = userMapper;
	}

	@GetMapping
	public List<UserDTO> findAll(@RequestParam(defaultValue = "0", required= false) Integer page, 
							 @RequestParam(defaultValue = "10", required= false) Integer size,
							 @PathVariable("idCourse") Integer idCourse) throws NotFoundException {
		final List<User> users = userService.findUserByCourse(idCourse, PageRequest.of(page, size));
		return userMapper.modelToDto(users);
	}
	
	@GetMapping("/{idUser}")
	public UserDTO findById(@PathVariable("idCourse") Integer idCourse,
			 @PathVariable("idUser") Integer idUser) throws NotFoundException {
		final User user = userService.findOneUserByCourse(idCourse, idUser);
		return userMapper.modelToDto(user);
	}
}
