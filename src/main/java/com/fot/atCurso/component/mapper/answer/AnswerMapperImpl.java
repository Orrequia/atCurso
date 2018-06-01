package com.fot.atCurso.component.mapper.answer;

import org.springframework.stereotype.Component;

import com.fot.atCurso.component.mapper.AbstractMapper;
import com.fot.atCurso.dto.answer.AnswerDTO;
import com.fot.atCurso.model.Answer;

@Component
public class AnswerMapperImpl extends AbstractMapper<Answer, AnswerDTO> implements AnswerMapper {

	@Override
	public Class<? extends AnswerDTO> dtoClazz() {
		return AnswerDTO.class;
	}

	@Override
	public Class<? extends Answer> modelClazz() {
		return Answer.class;
	}
}
