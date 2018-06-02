package com.fot.atCurso.component.mapper.question;

import com.fot.atCurso.component.mapper.Mapper;
import com.fot.atCurso.dto.question.QuestionDTO;
import com.fot.atCurso.dto.question.QuestionPostDTO;
import com.fot.atCurso.exception.NotFoundException;
import com.fot.atCurso.model.Question;

public interface QuestionMapper extends Mapper<Question, QuestionDTO> {

	Question dtoToModel(QuestionPostDTO dto) throws NotFoundException;
}
