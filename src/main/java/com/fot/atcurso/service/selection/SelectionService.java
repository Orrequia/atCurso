package com.fot.atcurso.service.selection;

import java.util.List;

import com.fot.atcurso.exception.AlreadyDoneException;
import com.fot.atcurso.exception.ExceededTimeException;
import com.fot.atcurso.exception.NotFoundException;
import com.fot.atcurso.model.Answer;
import com.fot.atcurso.model.Question;
import com.fot.atcurso.model.Quiz;
import com.fot.atcurso.model.Selection;
import com.fot.atcurso.model.User;
import com.fot.atcurso.service.AbstractService;

public interface SelectionService extends AbstractService<Selection, Integer> {

	boolean isFirstTime(User user, Quiz quiz);
	
	List<Selection> findByUserAndQuiz(User user, Quiz quiz);
	Selection create(User user, Quiz quiz, Question question);
	List<Selection> create(User user, Quiz quiz, List<Question> questions);
	
	void answerTheQuestion(User user, Quiz quiz, Question question, Answer answer) throws ExceededTimeException, AlreadyDoneException, NotFoundException;
	boolean allQuestionsBeenAnswered(User user, Quiz quiz);
	
	void deleteByUser(User user);
}
