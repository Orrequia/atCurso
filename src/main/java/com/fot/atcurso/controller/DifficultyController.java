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

import com.fot.atcurso.component.mapper.difficulty.DifficultyMapper;
import com.fot.atcurso.dto.difficulty.DifficultyDTO;
import com.fot.atcurso.exception.IdValueCannotBeReceivedException;
import com.fot.atcurso.exception.NotFoundException;
import com.fot.atcurso.exception.UnequalObjectsException;
import com.fot.atcurso.exception.IncorrectParametersException;
import com.fot.atcurso.model.Difficulty;
import com.fot.atcurso.service.difficulty.DifficultyService;

@RestController
@RequestMapping(value= "/difficulty")
public class DifficultyController {
	
	@Autowired
	DifficultyService difficultyService;
	
	@Autowired
	DifficultyMapper difficultyMapper;
	
	@GetMapping
	public List<DifficultyDTO> findAll(@RequestParam(defaultValue = "0", required= false ) Integer page, 
							 @RequestParam(defaultValue = "10", required= false ) Integer size) throws IncorrectParametersException {
		final List<Difficulty> difficultys = difficultyService.findAll(PageRequest.of(page, size));
		return difficultyMapper.modelToDto(difficultys);
	}
	
	@GetMapping("/{idDifficulty}")
	public DifficultyDTO findById(@PathVariable("idDifficulty") Integer id) throws NotFoundException {
		final Difficulty difficulty = difficultyService.getAndCheck(id);
		return difficultyMapper.modelToDto(difficulty);
	}
	
	@PostMapping
	public DifficultyDTO create(@RequestBody DifficultyDTO dto) throws IdValueCannotBeReceivedException, NotFoundException {
		if(dto.getIdDifficulty() != null) 
			throw new IdValueCannotBeReceivedException("El idDifficulty no se puede recibir en el body");
		final Difficulty difficulty = difficultyMapper.dtoToModel(dto);
		final Difficulty createDifficulty = difficultyService.create(difficulty);
		return difficultyMapper.modelToDto(createDifficulty);
	}
	
	@PutMapping("/{idDifficulty}")
	public void update(@PathVariable("idDifficulty") Integer id, @RequestBody DifficultyDTO dto) throws IdValueCannotBeReceivedException, NotFoundException {
		if(dto.getIdDifficulty() != null) 
			throw new IdValueCannotBeReceivedException("El idDifficulty no se puede recibir en el body");
		final Difficulty difficulty = difficultyService.getAndCheck(id);
		difficultyService.setValues(difficulty, difficultyMapper.dtoToModel(dto));
		difficultyService.update(difficulty);
	}
	
	@DeleteMapping("/{idDifficulty}")
	public void delete(@PathVariable("idDifficulty") Integer id, @RequestBody DifficultyDTO dto) throws NotFoundException, UnequalObjectsException {
		final Difficulty difficulty = difficultyService.getAndCheck(id);
		if(!difficultyService.isEqual(difficultyMapper.dtoToModel(dto), difficulty)) 
			throw new UnequalObjectsException("El usuario recibido no coincide con el almacenado");
		difficultyService.delete(difficulty);
	}
}
	