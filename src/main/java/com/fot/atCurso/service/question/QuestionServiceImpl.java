package com.fot.atCurso.service.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fot.atCurso.dao.QuestionDAO;
import com.fot.atCurso.model.Question;
import com.fot.atCurso.model.Result;
import com.fot.atCurso.service.AbstractServiceImpl;

@Service
public class QuestionServiceImpl extends AbstractServiceImpl<Question, QuestionDAO> implements QuestionService {
	
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
}
