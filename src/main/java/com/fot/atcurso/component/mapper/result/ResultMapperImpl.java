package com.fot.atcurso.component.mapper.result;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fot.atcurso.component.mapper.AbstractMapper;
import com.fot.atcurso.dto.result.ResultDTO;
import com.fot.atcurso.exception.NotFoundException;
import com.fot.atcurso.model.Quiz;
import com.fot.atcurso.model.Result;
import com.fot.atcurso.service.quiz.QuizService;

@Component
public class ResultMapperImpl extends AbstractMapper<Result, ResultDTO> implements ResultMapper {
	
	@Autowired
	QuizService quizService;
	
	@Override
	public Class<? extends ResultDTO> dtoClazz() {
		return ResultDTO.class;
	}
	
	@Override
	public Class<? extends Result> modelClazz() {
		return Result.class;
	}
	
	@Override
	public Result dtoToModel(ResultDTO dto) throws NotFoundException {
		Quiz quiz = integerToQuiz(dto.getIdQuiz());
		return map(dto, quiz);
	}
	
	@Override
	public ResultDTO modelToDto(Result model) {
		Integer quiz = quizToInteger(model.getQuiz());
		return map(model, quiz);
	}
	
	private Quiz integerToQuiz(Integer idQuiz) throws NotFoundException {
		if(idQuiz != null) {
			Optional<Quiz> quiz = quizService.findById(idQuiz);
			return quiz.orElseThrow(() -> new NotFoundException("El cuestionario no existe"));
		}
		return null;
	}
	
	private Integer quizToInteger(Quiz quiz) {
		return quiz.getIdQuiz();
	}
	
	private Result map(ResultDTO dto, Quiz quiz) {
		Result result = dozer.map(dto, modelClazz());
		result.setQuiz(quiz);
		return result;
	}
	
	private ResultDTO map(Result model, Integer quiz) {
		ResultDTO dto = dozer.map(model, dtoClazz());
		dto.setIdQuiz(quiz);
		return dto;
	}
}
