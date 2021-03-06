package com.fot.atcurso.component.mapper.answer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fot.atcurso.component.mapper.AbstractMapper;
import com.fot.atcurso.dto.answer.AnswerDTO;
import com.fot.atcurso.dto.answer.AnswerPostDTO;
import com.fot.atcurso.exception.NotFoundException;
import com.fot.atcurso.model.Answer;

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
	
	@Override
	public List<Answer> dtoPostToModel(List<AnswerPostDTO> dtos) throws NotFoundException {
		List<Answer> models = new ArrayList<>();
		if(dtos != null) 
			for(AnswerPostDTO dto : dtos)
				models.add(dtoToModel(dto));
		return models;
	}
}
