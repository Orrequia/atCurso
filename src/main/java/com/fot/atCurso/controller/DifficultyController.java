package com.fot.atCurso.controller;

import java.util.Optional;
import java.util.Set;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fot.atCurso.component.mapper.difficulty.DifficultyMapper;
import com.fot.atCurso.dto.difficulty.DifficultyDTO;
import com.fot.atCurso.exceptions.IdValueCannotBeReceivedException;
import com.fot.atCurso.exceptions.NotFoundException;
import com.fot.atCurso.exceptions.ParametersNotAllowedException;
import com.fot.atCurso.model.Difficulty;
import com.fot.atCurso.service.difficulty.DifficultyService;

@RestController
@RequestMapping(value= "/difficulty")
public class DifficultyController {
	
	@Autowired
	DifficultyService difficultyService;
	
	@Autowired
	DifficultyMapper difficultyMapper;
	
	@GetMapping
	public Set<DifficultyDTO> findAll(@RequestParam(defaultValue = "0", required= false ) Integer page, 
							 @RequestParam(defaultValue = "10", required= false ) Integer size) throws ParametersNotAllowedException {
		final Set<Difficulty> difficultys = difficultyService.findAll(PageRequest.of(page, size));
		return difficultyMapper.modelToDto(difficultys);
	}
	
	@GetMapping("/{idDifficulty}")
	public DifficultyDTO findById(@PathVariable("idDifficulty") Integer id) throws NotFoundException {
		final Optional<Difficulty> difficulty = difficultyService.findById(id);
		if (difficulty.isPresent()) return difficultyMapper.modelToDto(difficulty.get());
		throw new NotFoundException("El difficulty no existe");
	}
	
	@PostMapping
	public DifficultyDTO create(@RequestBody DifficultyDTO dto) throws IdValueCannotBeReceivedException, ConstraintViolationException, NotFoundException {
		if(dto.getIdDifficulty() != null) 
			throw new IdValueCannotBeReceivedException("El idDifficulty no se puede recibir");
		final Difficulty difficulty = difficultyMapper.dtoToModel(dto);
		final Difficulty createDifficulty = difficultyService.create(difficulty);
		return difficultyMapper.modelToDto(createDifficulty);
	}
}
	