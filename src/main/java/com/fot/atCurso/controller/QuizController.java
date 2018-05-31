package com.fot.atCurso.controller;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fot.atCurso.component.mapper.quiz.QuizMapper;
import com.fot.atCurso.dto.quiz.QuizDTO;
import com.fot.atCurso.exceptions.IdValueCannotBeReceivedException;
import com.fot.atCurso.exceptions.NotFoundException;
import com.fot.atCurso.exceptions.ParametersNotAllowedException;
import com.fot.atCurso.model.Quiz;
import com.fot.atCurso.service.quiz.QuizService;

@RestController
@RequestMapping(value= "/quiz")
public class QuizController {

	private static final Integer maxSize = new Integer(10);
	
	@Autowired
	QuizService quizService;
	
	@Autowired
	QuizMapper quizMapper;
	
	@GetMapping
	public Set<QuizDTO> findAll(@RequestParam(defaultValue = "0", required= false ) Integer page, 
							 @RequestParam(defaultValue = "10", required= false ) Integer size) throws ParametersNotAllowedException {
		if(page < 0 || size <= 0 || size > maxSize)
			throw new ParametersNotAllowedException("Los parámetros introducidos contienen valores no permitidos, page mayor o igual a 0 y size entre 1 y " + maxSize + " incluídos");
		final Set<Quiz> questionaries = quizService.findAll(PageRequest.of(page, size));
		return quizMapper.modelToDto(questionaries);
	}
	
	@GetMapping("/{idUser}")
	public QuizDTO findById(@PathVariable("idUser") Integer id) throws NotFoundException {
		final Optional<Quiz> quiz = quizService.findById(id);
		if (quiz.isPresent()) return quizMapper.modelToDto(quiz.get());
		throw new NotFoundException("El cuestionario no existe");
	}
	
	@PostMapping
	public QuizDTO create(@RequestBody QuizDTO dto) throws NotFoundException, IdValueCannotBeReceivedException {
		if(dto.getIdQuiz() != null)
			throw new IdValueCannotBeReceivedException("El idQuiz no se puede recibir");
		final Quiz quiz = quizMapper.dtoToModel(dto);
		final Quiz createQuiz = quizService.create(quiz);
		return quizMapper.modelToDto(createQuiz);
	}
	
	/*@RequestMapping(value = "/{idUser}", method = RequestMethod.PUT)
	public void update(@PathVariable("idUser") Integer id, @RequestBody UserPostDTO dto) throws IdValueCannotBeReceivedException, NotFoundException, UniqueValueViolationException {
		if(dto.getIdUser() != null) 
			throw new IdValueCannotBeReceivedException("El idUser no se puede recibir en el body");
		final Optional<User> user = quizService.findById(id);
		user.orElseThrow(() -> new NotFoundException("El usuario no existe"));
		if (user.get().getEmail() != dto.getEmail() && quizService.findByEmail(dto.getEmail()).isPresent()) 
			throw new UniqueValueViolationException("El email a cambiar ya existe");
		quizService.setValues(user.get(), quizMapper.dtoToModel(dto));
		quizService.update(user.get());
	}
	
	@RequestMapping(value = "/{idUser}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("idUser") Integer id, @RequestBody UserDTO dto) throws NotFoundException, ObjectsDoNotMatchException {
		final Optional<Quiz> user = quizService.findById(id);
		user.orElseThrow(() -> new NotFoundException("El usuario no existe"));
		if(!quizService.isEqual(quizMapper.dtoToModel(dto), user.get())) 
			throw new ObjectsDoNotMatchException("El usuario recibido no coincide con el almacenado");
		quizService.delete(user.get());
	}*/
}