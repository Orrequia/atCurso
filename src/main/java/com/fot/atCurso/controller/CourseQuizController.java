package com.fot.atCurso.controller;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
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
import com.fot.atCurso.exceptions.IdValueCannotBeReceivedException;
import com.fot.atCurso.exceptions.NotFoundException;
import com.fot.atCurso.exceptions.ObjectsDoNotMatchException;
import com.fot.atCurso.exceptions.ParametersNotAllowedException;
import com.fot.atCurso.model.Quiz;
import com.fot.atCurso.service.course.CourseService;
import com.fot.atCurso.service.quiz.QuizService;
import com.fot.atCurso.model.Course;

@RestController
@RequestMapping(value= "/course/{idCourse}/quiz")
public class CourseQuizController {
	
	@Autowired
	CourseService courseService;
	
	@Autowired
	QuizService quizService;
	
	@Autowired
	QuizMapper quizMapper;
	
	@GetMapping
	public Set<QuizDTO> findAll(@RequestParam(defaultValue = "0", required= false ) Integer page, 
							 @RequestParam(defaultValue = "10", required= false ) Integer size,
							 @PathVariable("idCourse") Integer idCourse) throws ParametersNotAllowedException, NotFoundException {
		final Optional<Course> course = courseService.findById(idCourse);
		course.orElseThrow(() -> new NotFoundException("El curso no existe"));
		final Set<Quiz> quizs = new PageImpl<Quiz>(course.get().getQuiz(), PageRequest.of(page, size), course.get().getQuiz().size())
				.stream().collect(Collectors.toSet());
		return quizMapper.modelToDto(quizs);
	}
	
	@GetMapping("/{idQuiz}")
	public QuizDTO findById(@PathVariable("idCourse") Integer idCourse,
			 @PathVariable("idQuiz") Integer idQuiz) throws NotFoundException {
		final Optional<Course> course = courseService.findById(idCourse);
		course.orElseThrow(() -> new NotFoundException("El usuario no existe"));
		final Optional<Quiz> quiz = courseService.searchQuiz(course.get(), idQuiz);
		course.orElseThrow(() -> new NotFoundException("Este quizado no existe para este usuario"));
		return quizMapper.modelToDto(quiz.get());
	}
	
	@PostMapping
	public QuizDTO create(@RequestBody QuizDTO dto,
			@PathVariable("idCourse") Integer idCourse) throws IdValueCannotBeReceivedException, ConstraintViolationException, NotFoundException {
		if(dto.getIdQuiz() != null) 
			throw new IdValueCannotBeReceivedException("El idQuiz no se puede recibir");
		final Optional<Course> course = courseService.findById(idCourse);
		course.orElseThrow(() -> new NotFoundException("El usuario no existe"));
		Quiz createQuiz = quizService.create(quizMapper.dtoToModel(dto));
		courseService.addQuiz(course.get(), createQuiz);
		return quizMapper.modelToDto(createQuiz);
	}
	
	@PutMapping("/{idQuiz}")
	public void update(@PathVariable("idCourse") Integer idCourse,
			@PathVariable("idQuiz") Integer idQuiz, 
		    @RequestBody QuizDTO dto) throws IdValueCannotBeReceivedException, NotFoundException {
		if(dto.getIdQuiz() != null) 
			throw new IdValueCannotBeReceivedException("El idQuiz no se puede recibir");
		final Optional<Course> course = courseService.findById(idCourse);
		course.orElseThrow(() -> new NotFoundException("El usuario no existe"));
		final Optional<Quiz> quiz = courseService.searchQuiz(course.get(), idQuiz);
		quiz.orElseThrow(() -> new NotFoundException("Este quizado no existe para este usuario"));
		quizService.setValues(quiz.get(), quizMapper.dtoToModel(dto));
		quizService.update(quiz.get());
	}
	
	@DeleteMapping("/{idQuiz}")
	public void delete(@PathVariable("idCourse") Integer idCourse,
			@PathVariable("idQuiz") Integer idQuiz, 
			@RequestBody QuizDTO dto) throws NotFoundException, ObjectsDoNotMatchException {
		final Optional<Course> course = courseService.findById(idCourse);
		course.orElseThrow(() -> new NotFoundException("El usuario no existe"));
		final Optional<Quiz> quiz = courseService.searchQuiz(course.get(), idQuiz);
		quiz.orElseThrow(() -> new NotFoundException("Este quizado no existe para este usuario"));
		if(!quizService.isEqual(quizMapper.dtoToModel(dto), quiz.get())) 
			throw new ObjectsDoNotMatchException("El quizado recibido no coincide con el almacenado");
		courseService.removeQuiz(course.get(), quiz.get());
		quizService.delete(quiz.get());
	}
}
