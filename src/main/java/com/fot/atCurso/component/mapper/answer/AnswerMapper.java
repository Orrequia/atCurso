package com.fot.atCurso.component.mapper.answer;

import java.util.List;

import com.fot.atCurso.component.mapper.Mapper;
import com.fot.atCurso.dto.answer.AnswerDTO;
import com.fot.atCurso.dto.answer.AnswerPostDTO;
import com.fot.atCurso.exception.NotFoundException;
import com.fot.atCurso.model.Answer;

public interface AnswerMapper extends Mapper<Answer, AnswerDTO> {

	List<Answer> dtoPostToModel(List<AnswerPostDTO> dtos) throws NotFoundException;
}
