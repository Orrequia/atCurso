package com.fot.atCurso.component.mapper.question;

import org.springframework.stereotype.Component;

import com.fot.atCurso.component.mapper.AbstractMapper;
import com.fot.atCurso.component.mapper.course.CourseMapper;
import com.fot.atCurso.dto.course.CourseDTO;
import com.fot.atCurso.dto.question.QuestionDTO;
import com.fot.atCurso.model.Course;
import com.fot.atCurso.model.Question;

@Component
public class QuestionMapperImpl extends AbstractMapper<Question, QuestionDTO> implements QuestionMapper {

	@Override
	public Class<? extends QuestionDTO> dtoClazz() {
		return QuestionDTO.class;
	}

	@Override
	public Class<? extends Question> modelClazz() {
		return Question.class;
	}
}
