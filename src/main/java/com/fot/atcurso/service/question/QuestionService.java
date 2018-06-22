package com.fot.atcurso.service.question;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.fot.atcurso.exception.RequirementsNotMetException;
import com.fot.atcurso.exception.AlreadyDoneException;
import com.fot.atcurso.exception.ConstraintBreakException;
import com.fot.atcurso.exception.NotFoundException;
import com.fot.atcurso.model.Question;
import com.fot.atcurso.model.Quiz;
import com.fot.atcurso.model.Tag;
import com.fot.atcurso.service.AbstractService;

public interface QuestionService  extends AbstractService<Question, Integer> {

	List<Question> findByTag(Integer idTag, Pageable p) throws NotFoundException;
	List<Question> findByTags(List<Tag> tags);
	
	boolean isEqual(Question q1, Question q2);
	void setValues(Question to, Question from);
	void deleteAll(Question q);
	
	Question getAndCheck(Integer idQuestion) throws NotFoundException;
	Question getAndCheckBelongQuiz(Quiz quiz, Integer idQuestion) throws NotFoundException;
	Question checkAndCreate(Question question) throws ConstraintBreakException;
	void checkAndUpdate(Question to, Question from) throws ConstraintBreakException;
	
	void checkConditionsUserAndQuiz(Integer idUser, Integer idQuiz) throws NotFoundException;
	
	List<Question> getAndCheckQuestions(Integer idUser, Integer idQuiz) throws NotFoundException, RequirementsNotMetException, AlreadyDoneException;
}
