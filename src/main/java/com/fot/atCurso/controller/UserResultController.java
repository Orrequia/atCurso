package com.fot.atCurso.controller;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
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

import com.fot.atCurso.component.mapper.result.ResultMapper;
import com.fot.atCurso.dto.result.ResultDTO;
import com.fot.atCurso.exceptions.IdValueCannotBeReceivedException;
import com.fot.atCurso.exceptions.NotFoundException;
import com.fot.atCurso.exceptions.ObjectsDoNotMatchException;
import com.fot.atCurso.exceptions.ParametersNotAllowedException;
import com.fot.atCurso.model.Result;
import com.fot.atCurso.service.result.ResultService;

@RestController
@RequestMapping(value= "/user/{idUser}/result")
public class UserResultController {
	
	@Autowired
	ResultService resultService;
	
	@Autowired
	ResultMapper resultMapper;
	
	@GetMapping
	public List<ResultDTO> findAll(@RequestParam(defaultValue = "0", required= false ) Integer page, 
							 @RequestParam(defaultValue = "10", required= false ) Integer size,
							 @PathVariable("idUser") Integer idUser) throws ParametersNotAllowedException, NotFoundException {
		final List<Result> results = resultService.findResultByUser(idUser, PageRequest.of(page, size));
		return resultMapper.modelToDto(results);
	}
	
	@GetMapping("/{idResult}")
	public ResultDTO findById(@PathVariable("idUser") Integer idUser,
			 @PathVariable("idResult") Integer idResult) throws NotFoundException {
		final Result result = resultService.findOneResultByUser(idUser, idResult);
		return resultMapper.modelToDto(result);
	}
	
	@PostMapping
	public ResultDTO create(@RequestBody ResultDTO dto,
			@PathVariable("idUser") Integer idUser) throws IdValueCannotBeReceivedException, ConstraintViolationException, NotFoundException {
		if(dto.getIdResult() != null) 
			throw new IdValueCannotBeReceivedException("El idResult no se puede recibir");
		Result createResult = resultService.addToUser(idUser, resultMapper.dtoToModel(dto));
		return resultMapper.modelToDto(createResult);
	}
	
	@PutMapping("/{idResult}")
	public void update(@PathVariable("idUser") Integer idUser,
			@PathVariable("idResult") Integer idResult, 
		    @RequestBody ResultDTO dto) throws IdValueCannotBeReceivedException, NotFoundException {
		if(dto.getIdResult() != null) 
			throw new IdValueCannotBeReceivedException("El idResult no se puede recibir");
		resultService.updateToUser(idUser, idResult, resultMapper.dtoToModel(dto));
	}
	
	@DeleteMapping("/{idResult}")
	public void delete(@PathVariable("idUser") Integer idUser,
			@PathVariable("idResult") Integer idResult, 
			@RequestBody ResultDTO dto) throws NotFoundException, ObjectsDoNotMatchException {
		resultService.deleteToUser(idUser, idResult, resultMapper.dtoToModel(dto));
	}
}
