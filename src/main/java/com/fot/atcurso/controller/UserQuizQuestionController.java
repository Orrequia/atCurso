package com.fot.atcurso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fot.atcurso.component.mapper.question.QuestionMapper;
import com.fot.atcurso.dto.question.QuestionDTO;
import com.fot.atcurso.exception.RequirementsNotMetException;
import com.fot.atcurso.exception.AlreadyDoneException;
import com.fot.atcurso.exception.NotFoundException;
import com.fot.atcurso.service.question.QuestionService;

@RestController
@RequestMapping(value= "/user/{idUser}/quiz/{idQuiz}/question")
public class UserQuizQuestionController {

	@Autowired
	QuestionService questionService;
	
	@Autowired
	QuestionMapper questionMapper;
	
	@GetMapping
	public List<QuestionDTO> getQuestions(@PathVariable("idUser") Integer idUser, 
			@PathVariable("idQuiz") Integer idQuiz) throws NotFoundException, RequirementsNotMetException, AlreadyDoneException {
		return questionMapper.modelToDto(questionService.getAndCheckQuestions(idUser, idQuiz));
	}
}
