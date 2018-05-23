package com.fot.atCurso.controller;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fot.atCurso.component.mapper.user.UserMapper;
import com.fot.atCurso.dto.user.UserDTO;
import com.fot.atCurso.dto.user.UserPostDTO;
import com.fot.atCurso.model.User;
import com.fot.atCurso.service.user.UserService;

 
@RestController
@RequestMapping(value= "/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	UserMapper userMapper;
	
	@RequestMapping(method = RequestMethod.GET)
	public Set<UserDTO> findAll(@RequestParam(defaultValue = "0", required= false ) Integer page, 
							 @RequestParam(defaultValue = "10", required= false ) Integer size) {
		final Set<User> users = userService.findAll(PageRequest.of(page, size));
		return userMapper.modelToDto(users);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public UserDTO create(@RequestBody UserPostDTO dto) {
		final User user = userMapper.dtoToModel(dto);
		final User createUser = userService.create(user);
		return userMapper.modelToDto(createUser);
	}
}
