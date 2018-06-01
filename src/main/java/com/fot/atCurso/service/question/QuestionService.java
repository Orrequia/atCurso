package com.fot.atCurso.service.question;

import com.fot.atCurso.model.Question;
import com.fot.atCurso.service.AbstractService;

public interface QuestionService  extends AbstractService<Question, Integer> {

	boolean isEqual(Question q1, Question q2);
	void setValues(Question to, Question from);
}
