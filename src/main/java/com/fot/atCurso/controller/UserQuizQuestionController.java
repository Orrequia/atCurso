package com.fot.atCurso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fot.atCurso.component.mapper.question.QuestionMapper;
import com.fot.atCurso.exception.CannotGetNewQuestionWithAnswerBeforeException;
import com.fot.atCurso.exception.CompletedQuizException;
import com.fot.atCurso.exception.NotFoundException;
import com.fot.atCurso.exception.ParametersNotAllowedException;
import com.fot.atCurso.model.Question;
import com.fot.atCurso.service.question.QuestionService;

@RestController
@RequestMapping(value= "/user/{idUser}/quiz/{idQuiz}/question")
public class UserQuizQuestionController {

	@Autowired
	QuestionService questionService;
	
	@Autowired
	QuestionMapper questionMapper;
	
	@GetMapping
	public List<Question> getQuestions(@RequestParam(defaultValue="0", required=false) Integer page, 
			 	@RequestParam(defaultValue="10", required=false) Integer size,
			 	@PathVariable("idUser") Integer idUser, @PathVariable("idQuiz") Integer idQuiz) throws ParametersNotAllowedException, NotFoundException, CannotGetNewQuestionWithAnswerBeforeException, CompletedQuizException {
		return questionService.getAndCheckQuestions(idUser, idQuiz);
	}
}
