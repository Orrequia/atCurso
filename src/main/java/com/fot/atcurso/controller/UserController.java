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

import com.fot.atcurso.component.mapper.user.UserMapper;
import com.fot.atcurso.dto.user.UserDTO;
import com.fot.atcurso.dto.user.UserPostDTO;
import com.fot.atcurso.exception.ConstraintBreakException;
import com.fot.atcurso.exception.IdValueCannotBeReceivedException;
import com.fot.atcurso.exception.NotFoundException;
import com.fot.atcurso.exception.UnequalObjectsException;
import com.fot.atcurso.exception.IncorrectParametersException;
import com.fot.atcurso.model.User;
import com.fot.atcurso.service.user.UserService;
 
@RestController
@RequestMapping(value= "/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserMapper userMapper;
	
	@GetMapping
	public List<UserDTO> findAll(@RequestParam(defaultValue = "0", required= false ) Integer page, 
							 @RequestParam(defaultValue = "10", required= false ) Integer size) throws IncorrectParametersException {
		final List<User> users = userService.findAll(PageRequest.of(page, size));
		return userMapper.modelToDto(users);
	}
	
	@GetMapping("/{idUser}")
	public UserDTO findById(@PathVariable("idUser") Integer id) throws NotFoundException {
		final User user = userService.getAndCheck(id);
		return userMapper.modelToDto(user);
	}
	
	@PostMapping
	public UserDTO create(@RequestBody UserPostDTO dto) throws IdValueCannotBeReceivedException, NotFoundException, ConstraintBreakException {
		if(dto.getIdUser() != null) 
			throw new IdValueCannotBeReceivedException("El idUser no se puede recibir en el body");
		final User user = userMapper.dtoToModel(dto);
		userService.checkValues(user);
		final User createUser = userService.create(user);
		return userMapper.modelToDto(createUser);
	}
	
	@PutMapping("/{idUser}")
	public void update(@PathVariable("idUser") Integer id, @RequestBody UserPostDTO dto) throws IdValueCannotBeReceivedException, NotFoundException, ConstraintBreakException {
		if(dto.getIdUser() != null) 
			throw new IdValueCannotBeReceivedException("El idUser no se puede recibir en el body");
		final User user = userService.getAndCheck(id);
		userService.checkValues(user);
		userService.setValues(user, userMapper.dtoToModel(dto));
		userService.update(user);
	}
	
	@DeleteMapping("/{idUser}")
	public void delete(@PathVariable("idUser") Integer id, @RequestBody UserDTO dto) throws NotFoundException, UnequalObjectsException {
		final User user = userService.getAndCheck(id);
		if(!userService.isEqual(userMapper.dtoToModel(dto), user)) 
			throw new UnequalObjectsException("El usuario recibido no coincide con el almacenado");
		userService.removeAllSelections(user);
		userService.delete(user);
	}
}
