package com.fot.atCurso.controller;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fot.atCurso.component.mapper.result.ResultMapper;
import com.fot.atCurso.dto.result.ResultDTO;
import com.fot.atCurso.dto.user.UserDTO;
import com.fot.atCurso.dto.user.UserPostDTO;
import com.fot.atCurso.exceptions.IdValueCannotBeReceivedException;
import com.fot.atCurso.exceptions.NotFoundException;
import com.fot.atCurso.exceptions.ParametersNotAllowedException;
import com.fot.atCurso.model.Result;
import com.fot.atCurso.model.User;
import com.fot.atCurso.service.user.UserService;

@RestController
@RequestMapping(value= "/user/{idUser}/result")
public class UserResultController {

	@Autowired
	UserService userService;
	
	@Autowired
	ResultMapper resultMapper;
	
	@GetMapping
	public Set<ResultDTO> findAll(@RequestParam(defaultValue = "0", required= false ) Integer page, 
							 @RequestParam(defaultValue = "10", required= false ) Integer size,
							 @PathVariable("idUser") Integer idUser) throws ParametersNotAllowedException, NotFoundException {
		final Optional<User> user = userService.findById(idUser);
		user.orElseThrow(() -> new NotFoundException("El usuario no existe"));
		final Set<Result> results = new PageImpl<Result>(user.get().getResult(), PageRequest.of(page, size), user.get().getResult().size())
				.stream().collect(Collectors.toSet());
		return resultMapper.modelToDto(results);
	}
	
	@GetMapping("/{idResult}")
	public ResultDTO findById(@RequestParam(defaultValue = "0", required= false ) Integer page, 
			 @RequestParam(defaultValue = "10", required= false ) Integer size,
			 @PathVariable("idUser") Integer idUser,
			 @PathVariable("idResult") Integer idResult) throws ParametersNotAllowedException, NotFoundException {
		final Optional<User> user = userService.findById(idUser);
		user.orElseThrow(() -> new NotFoundException("El usuario no existe"));
		final Optional<Result> result = user.get().getResult().stream().filter(r -> r.getIdResult() == idResult).findFirst();
		user.orElseThrow(() -> new NotFoundException("El resultado no existe"));
		return resultMapper.modelToDto(result.get());
	}
}
