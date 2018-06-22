package com.fot.atcurso.service.answer;

import com.fot.atcurso.exception.AlreadyDoneException;
import com.fot.atcurso.exception.ExceededTimeException;
import com.fot.atcurso.exception.NotFoundException;
import com.fot.atcurso.model.Answer;
import com.fot.atcurso.model.Question;
import com.fot.atcurso.service.AbstractService;

public interface AnswerService extends AbstractService<Answer, Integer> {

	Answer addAnswerToSelection(Integer idUser, Integer idQuiz, Integer idQuestion, Answer answer) throws NotFoundException, ExceededTimeException, AlreadyDoneException;
	
	Answer getCorrect(Question question);
}
