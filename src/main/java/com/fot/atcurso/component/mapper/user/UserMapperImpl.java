package com.fot.atcurso.component.mapper.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fot.atcurso.component.mapper.AbstractMapper;
import com.fot.atcurso.dto.user.UserDTO;
import com.fot.atcurso.exception.NotFoundException;
import com.fot.atcurso.model.Result;
import com.fot.atcurso.model.User;
import com.fot.atcurso.service.result.ResultService;
import com.fot.atcurso.service.user.UserService;

@Component
public class UserMapperImpl extends AbstractMapper<User, UserDTO> implements UserMapper{

	@Autowired
	ResultService resultService;
	
	@Autowired
	UserService userService;
	
	@Override
	public Class<? extends UserDTO> dtoClazz() {
		return UserDTO.class;
	}

	@Override
	public Class<? extends User> modelClazz() {
		return User.class;
	}
	
	@Override
	public User dtoToModel(UserDTO dto) throws NotFoundException {
		List<Result> result = integerToResult(dto.getResults());
		return map(dto, result);
	}
	
	@Override
	public UserDTO modelToDto(User model) {
		List<Integer> results = resultToInteger(model.getResult());
		return map(model, results);
	}
	
	private List<Result> integerToResult(List<Integer> results) throws NotFoundException {
		if(results != null) {
			List<Result> result = results.stream().map(iR -> resultService.findById(iR))
												.filter(Optional::isPresent)
												.map(Optional::get)
												.collect(Collectors.toList());
			if(result.size() != results.size()) throw new NotFoundException("Algunos o todos los tags no existen");
			return result;
		}
		return new ArrayList<>();
	}
	
	private List<Integer> resultToInteger(List<Result> result) {
		return result.stream().map(Result::getIdResult).collect(Collectors.toList());
	}
	
	private User map(UserDTO dto, List<Result> result) {
		User user = dozer.map(dto, modelClazz());
		user.setResult(result);
		return user;
	}
	
	private UserDTO map(User model, List<Integer> results) {
		UserDTO dto = dozer.map(model, dtoClazz());
		dto.setResults(results);
		return dto;
	}
}
