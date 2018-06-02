package com.fot.atCurso.controller;

import java.util.List;
import java.util.Optional;

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

import com.fot.atCurso.component.mapper.question.QuestionMapper;
import com.fot.atCurso.dto.question.QuestionDTO;
import com.fot.atCurso.dto.question.QuestionPostDTO;
import com.fot.atCurso.exception.ConstraintBreakException;
import com.fot.atCurso.exception.IdValueCannotBeReceivedException;
import com.fot.atCurso.exception.NotFoundException;
import com.fot.atCurso.exception.UnequalObjectsException;
import com.fot.atCurso.exception.ParametersNotAllowedException;
import com.fot.atCurso.model.Question;
import com.fot.atCurso.service.question.QuestionService;

@RestController
@RequestMapping(value= "/question")
public class QuestionController {
	
	@Autowired
	QuestionService questionService;
	
	@Autowired
	QuestionMapper questionMapper;
		
	@GetMapping
	public List<QuestionDTO> findAll(@RequestParam(defaultValue = "0", required= false ) Integer page, 
			 	@RequestParam(defaultValue = "10", required= false ) Integer size) throws ParametersNotAllowedException {
		final List<Question> questions = questionService.findAll(PageRequest.of(page, size));
		return questionMapper.modelToDto(questions);
	}
	
	@GetMapping("/{idQuestion}")
	public QuestionDTO findById(@PathVariable("idQuestion") Integer idQuestion) throws NotFoundException {
		final Optional<Question> question = questionService.findById(idQuestion);
		if (question.isPresent()) return questionMapper.modelToDto(question.get());
		throw new NotFoundException("El curso no existe");
	}
	
	@PostMapping
	public QuestionDTO create(@RequestBody QuestionPostDTO dto) throws IdValueCannotBeReceivedException, NotFoundException, ConstraintBreakException {
		if(dto.getIdQuestion() != null) 
			throw new IdValueCannotBeReceivedException("El idQuestion no se puede recibir");
		final Question question = questionMapper.dtoToModel(dto);
		final Question createQuestion = questionService.checkAndCreate(question);
		return questionMapper.modelToDto(createQuestion);
	}
	
	@PutMapping("/{idQuestion}")
	public void update(@PathVariable("idQuestion") Integer id, @RequestBody QuestionPostDTO dto) throws IdValueCannotBeReceivedException, NotFoundException, ConstraintBreakException {
		if(dto.getIdQuestion() != null) 
			throw new IdValueCannotBeReceivedException("El idQuestion no se puede recibir en el body");
		final Question question = questionService.getAndCheck(id);
		questionService.setValues(question, questionMapper.dtoToModel(dto));
		questionService.checkAndUpdate(question);
	}
	
	@DeleteMapping("/{idQuestion}")
	public void delete(@PathVariable("idQuestion") Integer id, @RequestBody QuestionDTO dto) throws NotFoundException, UnequalObjectsException, ConstraintBreakException {
		final Question question = questionService.getAndCheck(id);
		if(!questionService.isEqual(questionMapper.dtoToModel(dto), question)) 
			throw new UnequalObjectsException("El usuario recibido no coincide con el almacenado");
		questionService.delete(question);
	}
}
