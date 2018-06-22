package com.fot.atcurso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fot.atcurso.component.mapper.answer.AnswerMapper;
import com.fot.atcurso.dto.answer.AnswerDTO;
import com.fot.atcurso.exception.AlreadyDoneException;
import com.fot.atcurso.exception.ExceededTimeException;
import com.fot.atcurso.exception.NotFoundException;
import com.fot.atcurso.service.answer.AnswerService;

@RestController
@RequestMapping(value= "/user/{idUser}/quiz/{idQuiz}/question/{idQuestion}")
public class UserQuizQuestionAnswerController {

	@Autowired
	AnswerService answerService;
	
	@Autowired
	AnswerMapper answerMapper;
	
	@PostMapping
	public AnswerDTO respond(@RequestBody AnswerDTO dto,
			@PathVariable("idUser") Integer idUser,
			@PathVariable("idQuiz") Integer idQuiz,
			@PathVariable("idQuestion") Integer idQuestion) throws NotFoundException, ExceededTimeException, AlreadyDoneException {
		return answerMapper.modelToDto(answerService.addAnswerToSelection(idUser, idQuiz, idQuestion, answerMapper.dtoToModel(dto)));
	}
}
