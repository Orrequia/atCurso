package com.fot.atCurso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fot.atCurso.component.mapper.question.QuestionMapper;
import com.fot.atCurso.dto.question.QuestionDTO;
import com.fot.atCurso.exception.RequirementsNotMetException;
import com.fot.atCurso.exception.AlreadyDoneException;
import com.fot.atCurso.exception.NotFoundException;
import com.fot.atCurso.service.question.QuestionService;

@RestController
@RequestMapping(value= "/user/{idUser}/quiz/{idQuiz}/question")
public class UserQuizQuestionController {

	private final QuestionService questionService;
	private final QuestionMapper questionMapper;

	@Autowired
	public UserQuizQuestionController(QuestionService questionService, QuestionMapper questionMapper) {
		this.questionService = questionService;
		this.questionMapper = questionMapper;
	}

	@GetMapping
	public List<QuestionDTO> getQuestions(@PathVariable("idUser") Integer idUser, 
			@PathVariable("idQuiz") Integer idQuiz) throws NotFoundException, RequirementsNotMetException, AlreadyDoneException {
		return questionMapper.modelToDto(questionService.getAndCheckQuestions(idUser, idQuiz));
	}
}
