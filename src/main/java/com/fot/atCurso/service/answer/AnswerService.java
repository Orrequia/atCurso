package com.fot.atCurso.service.answer;

import com.fot.atCurso.exception.NotFoundException;
import com.fot.atCurso.model.Answer;
import com.fot.atCurso.service.AbstractService;

public interface AnswerService extends AbstractService<Answer, Integer> {

	Answer addAnswerToSelection(Integer idUser, Integer idQuiz, Integer idQuestion, Answer answer) throws NotFoundException;
}
