package com.fot.atcurso.component.mapper.answer;

import java.util.List;

import com.fot.atcurso.component.mapper.Mapper;
import com.fot.atcurso.dto.answer.AnswerDTO;
import com.fot.atcurso.dto.answer.AnswerPostDTO;
import com.fot.atcurso.exception.NotFoundException;
import com.fot.atcurso.model.Answer;

public interface AnswerMapper extends Mapper<Answer, AnswerDTO> {

	List<Answer> dtoPostToModel(List<AnswerPostDTO> dtos) throws NotFoundException;
}
