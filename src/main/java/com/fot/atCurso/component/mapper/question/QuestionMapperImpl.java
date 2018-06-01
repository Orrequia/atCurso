package com.fot.atCurso.component.mapper.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fot.atCurso.component.mapper.AbstractMapper;
import com.fot.atCurso.component.mapper.answer.AnswerMapper;
import com.fot.atCurso.dto.question.QuestionDTO;
import com.fot.atCurso.exceptions.NotFoundException;
import com.fot.atCurso.model.Question;


@Component
public class QuestionMapperImpl extends AbstractMapper<Question, QuestionDTO> implements QuestionMapper {

	@Autowired
	AnswerMapper answerMapper;
	
	@Override
	public Class<? extends QuestionDTO> dtoClazz() {
		return QuestionDTO.class;
	}

	@Override
	public Class<? extends Question> modelClazz() {
		return Question.class;
	}
	
	@Override
	public Question dtoToModel(QuestionDTO dto) throws NotFoundException {
		Question question = dozer.map(dto, modelClazz());
		question.setAnswer(answerMapper.dtoToModel(dto.getAnwers()));
		return question;
	}
	
	@Override
	public QuestionDTO modelToDto(Question model) {
		QuestionDTO questionDTO = dozer.map(model, dtoClazz());
		questionDTO.setAnwers(answerMapper.modelToDto(model.getAnswer()));
		return questionDTO;
	}
}
