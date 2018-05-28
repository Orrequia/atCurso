package com.fot.atCurso.component.mapper.questionary;

import org.springframework.stereotype.Component;

import com.fot.atCurso.component.mapper.AbstractMapper;
import com.fot.atCurso.dto.questionary.QuestionaryDTO;
import com.fot.atCurso.model.Questionary;

@Component
public class QuestionaryMapperImpl extends AbstractMapper<Questionary, QuestionaryDTO> implements QuestionaryMapper {

	@Override
	public Class<? extends QuestionaryDTO> dtoClazz() {
		return QuestionaryDTO.class;
	}

	@Override
	public Class<? extends Questionary> modelClazz() {
		return Questionary.class;
	}
}
