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

import com.fot.atCurso.component.mapper.quiz.QuizMapper;
import com.fot.atCurso.dto.quiz.QuizDTO;
import com.fot.atCurso.exception.ConstraintBreakException;
import com.fot.atCurso.exception.IdValueCannotBeReceivedException;
import com.fot.atCurso.exception.NotFoundException;
import com.fot.atCurso.exception.UnequalObjectsException;
import com.fot.atCurso.model.Quiz;
import com.fot.atCurso.service.quiz.QuizService;

@RestController
@RequestMapping(value= "/course/{idCourse}/quiz")
public class CourseQuizController {
	
	private final QuizService quizService;
	private final QuizMapper quizMapper;

	@Autowired
	public CourseQuizController(QuizService quizService, QuizMapper quizMapper) {
		this.quizMapper = quizMapper;
		this.quizService = quizService;
	}

	@GetMapping
	public List<QuizDTO> findAll(@RequestParam(defaultValue = "0", required= false ) Integer page, 
							 @RequestParam(defaultValue = "10", required= false ) Integer size,
							 @PathVariable("idCourse") Integer idCourse) throws NotFoundException {
		final List<Quiz> results = quizService.findQuizByCourse(idCourse, PageRequest.of(page, size));
		return quizMapper.modelToDto(results);
	}
	
	@GetMapping("/{idQuiz}")
	public QuizDTO findById(@PathVariable("idCourse") Integer idCourse,
			 @PathVariable("idQuiz") Integer idQuiz) throws NotFoundException {
		final Quiz result = quizService.findOneQuizByCourse(idCourse, idQuiz);
		return quizMapper.modelToDto(result);
	}
	
	@PostMapping
	public QuizDTO create(@RequestBody QuizDTO dto,
			@PathVariable("idCourse") Integer idCourse,
			@RequestParam(defaultValue = "false", required = false) Boolean autoQuestions,
			@RequestParam(defaultValue = "10", required = false) Integer nQuestions) throws IdValueCannotBeReceivedException, ConstraintViolationException, NotFoundException, ConstraintBreakException {
		Quiz quiz = throwAndGetQuiz(dto, autoQuestions, nQuestions);
		return quizMapper.modelToDto(quizService.addToCourse(idCourse, quiz));
	}
	
	@PutMapping("/{idQuiz}")
	public void update(@PathVariable("idCourse") Integer idCourse,
			@PathVariable("idQuiz") Integer idQuiz, 
			@RequestParam(defaultValue = "false", required = false) Boolean autoQuestions,
			@RequestParam(defaultValue = "10", required = false) Integer nQuestions,
		    @RequestBody QuizDTO dto) throws IdValueCannotBeReceivedException, NotFoundException, ConstraintBreakException {
		Quiz quiz = throwAndGetQuiz(dto, autoQuestions, nQuestions);
		quizService.updateToCourse(idCourse, idQuiz, quiz);
	}
	
	@DeleteMapping("/{idQuiz}")
	public void delete(@PathVariable("idCourse") Integer idCourse,
			@PathVariable("idQuiz") Integer idQuiz, 
			@RequestBody QuizDTO dto) throws NotFoundException, UnequalObjectsException {
		quizService.deleteToCourse(idCourse, idQuiz, quizMapper.dtoToModel(dto));
	}

	private Quiz throwAndGetQuiz(QuizDTO dto, Boolean autoQuestions, Integer nQuestions) throws IdValueCannotBeReceivedException, NotFoundException, ConstraintBreakException {

		if(dto.getIdQuiz() != null)
			throw new IdValueCannotBeReceivedException("El idQuiz no se puede recibir en el body");
		Quiz quiz = quizMapper.dtoToModel(dto);
		if(autoQuestions)
			quizService.generateQuestions(quiz, nQuestions);
		return quiz;
	}
}
