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

import com.fot.atcurso.component.mapper.question.QuestionMapper;
import com.fot.atcurso.dto.question.QuestionDTO;
import com.fot.atcurso.dto.question.QuestionPostDTO;
import com.fot.atcurso.exception.ConstraintBreakException;
import com.fot.atcurso.exception.IdValueCannotBeReceivedException;
import com.fot.atcurso.exception.NotFoundException;
import com.fot.atcurso.exception.UnequalObjectsException;
import com.fot.atcurso.exception.IncorrectParametersException;
import com.fot.atcurso.model.Question;
import com.fot.atcurso.service.question.QuestionService;

@RestController
@RequestMapping(value= "/question")
class QuestionController {
	
	@Autowired
	QuestionService questionService;
	
	@Autowired
	QuestionMapper questionMapper;
		
	@GetMapping
	public List<QuestionDTO> findAll(@RequestParam(defaultValue = "0", required= false ) Integer page, 
			 	@RequestParam(defaultValue = "10", required= false ) Integer size,
			 	@RequestParam(defaultValue = "0", required=false) Integer tag) throws IncorrectParametersException, NotFoundException {
		List<Question> questions;
		if(tag != 0) questions = questionService.findByTag(tag, PageRequest.of(page, size));
		else questions = questionService.findAll(PageRequest.of(page, size));
		return questionMapper.modelToDto(questions);
	}
	
	@GetMapping("/{idQuestion}")
	public QuestionDTO findById(@PathVariable("idQuestion") Integer idQuestion) throws NotFoundException {
		final Question question = questionService.getAndCheck(idQuestion);
		return questionMapper.modelToDto(question);
	}
	
	@PostMapping
	public QuestionDTO create(@RequestBody QuestionPostDTO dto) throws IdValueCannotBeReceivedException, NotFoundException, ConstraintBreakException {
		if(dto.getIdQuestion() != null) 
			throw new IdValueCannotBeReceivedException("El idQuestion no se puede recibir en el body");
		final Question question = questionMapper.dtoToModel(dto);
		final Question createQuestion = questionService.checkAndCreate(question);
		return questionMapper.modelToDto(createQuestion);
	}
	
	@PutMapping("/{idQuestion}")
	public void update(@PathVariable("idQuestion") Integer id, @RequestBody QuestionPostDTO dto) throws IdValueCannotBeReceivedException, NotFoundException, ConstraintBreakException {
		if(dto.getIdQuestion() != null) 
			throw new IdValueCannotBeReceivedException("El idQuestion no se puede recibir en el body");
		Question question = questionService.getAndCheck(id);
		questionService.checkAndUpdate(question, questionMapper.dtoToModel(dto));
	}
	
	@DeleteMapping("/{idQuestion}")
	public void delete(@PathVariable("idQuestion") Integer id, @RequestBody QuestionDTO dto) throws NotFoundException, UnequalObjectsException {
		final Question question = questionService.getAndCheck(id);
		if(!questionService.isEqual(questionMapper.dtoToModel(dto), question)) 
			throw new UnequalObjectsException("La pregunta recibida no coincide con la almacenada");
		questionService.deleteAll(question);
	}
}
