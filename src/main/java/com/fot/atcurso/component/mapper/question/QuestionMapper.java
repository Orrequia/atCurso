package com.fot.atcurso.component.mapper.question;

import com.fot.atcurso.component.mapper.Mapper;
import com.fot.atcurso.dto.question.QuestionDTO;
import com.fot.atcurso.dto.question.QuestionPostDTO;
import com.fot.atcurso.exception.NotFoundException;
import com.fot.atcurso.model.Question;

public interface QuestionMapper extends Mapper<Question, QuestionDTO> {

	Question dtoToModel(QuestionPostDTO dto) throws NotFoundException;
}
