package com.fot.atCurso.service.question;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fot.atCurso.dao.QuestionDAO;
import com.fot.atCurso.exception.ConstraintBreakException;
import com.fot.atCurso.model.Question;
import com.fot.atCurso.model.Result;
import com.fot.atCurso.service.AbstractServiceImpl;

@Service
public class QuestionServiceImpl extends AbstractServiceImpl<Question, QuestionDAO> implements QuestionService {
	
	private static final Integer maxAnswers = 4;
	@Autowired
	QuestionDAO questionDAO;
	
	@Override
	public boolean isEqual(Question q1, Question q2) {
		return q1.getName().equals(q2.getName()) &&
				q1.getTag().equals(q2.getTag()) &&
				q1.getDifficulty().equals(q2.getDifficulty()) &&
				q1.getAnswer().equals(q2.getAnswer());
	}
	
	@Override 
	public void setValues(Question to, Question from) {
		to.setName(from.getName());
		to.setTag(from.getTag());
		to.setDifficulty(from.getDifficulty());
		to.setAnswer(from.getAnswer());
	}
	
	@Override
	public Question checkAndCreate(Question question) throws ConstraintBreakException {
		if(validate(question))
			return questionDAO.save(question);
		throw new ConstraintBreakException("El número de respuestas es incorrecto (1-" + maxAnswers + ") y solo debe existir una correcta.");
	}
	
	@Override
	public void checkAndUpdate(Question question) throws ConstraintBreakException {
		checkAndCreate(question);
	}
	
	private boolean validate(Question question) {
		if(question.getAnswer().size() > 0 && question.getAnswer().size() <= maxAnswers)
			return question.getAnswer().stream().filter(a -> a.getCorrect()).collect(Collectors.toList()).size() == 1;
		return false;
	}
}
