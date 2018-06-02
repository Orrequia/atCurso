package com.fot.atCurso.service.question;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fot.atCurso.dao.QuestionDAO;
import com.fot.atCurso.exception.ConstraintBreakException;
import com.fot.atCurso.exception.NotFoundException;
import com.fot.atCurso.model.Answer;
import com.fot.atCurso.model.Question;
import com.fot.atCurso.model.Tag;
import com.fot.atCurso.service.AbstractServiceImpl;
import com.fot.atCurso.service.answer.AnswerService;
import com.fot.atCurso.service.tag.TagService;

@Service
public class QuestionServiceImpl extends AbstractServiceImpl<Question, QuestionDAO> implements QuestionService {
	
	private static final Integer maxAnswers = 4;
	
	@Autowired
	QuestionDAO questionDAO;
	
	@Autowired
	AnswerService answerService;
	
	@Autowired
	TagService tagService;
	
	@Override
	public List<Question> findByTag(Integer idTag, Pageable p) throws NotFoundException {
		Tag tag = tagService.getAndCheck(idTag);
		return questionDAO.findByTag(tag, p);
	}
	
	@Override
	public boolean isEqual(Question q1, Question q2) {
		return q1.getName().equals(q2.getName()) &&
				q1.getTag().equals(q2.getTag()) &&
				q1.getDifficulty().equals(q2.getDifficulty()) &&
				CollectionUtils.isEqualCollection(getStringsAnswer(q1.getAnswer()),
												  getStringsAnswer(q2.getAnswer()));
	}
	
	private List<String> getStringsAnswer(List<Answer> answers) {
		List<String> sAnswers = new ArrayList<String>();
		for(Answer a : answers) 
			sAnswers.add(a.getName());
		return sAnswers;
	}
	
	@Override 
	public void setValues(Question to, Question from) {
		to.setName(from.getName());
		to.setTag(from.getTag());
		to.setDifficulty(from.getDifficulty());
		updateAnswers(to, from);
	}
	
	@Override
	public Question checkAndCreate(Question question) throws ConstraintBreakException {
		if(validate(question)) {
			addNewsAnswers(question);
			return questionDAO.save(question);
		}
		throw new ConstraintBreakException("El número de respuestas es incorrecto (1-" + maxAnswers + ") y solo debe existir una correcta.");
	}
	
	@Override
	public void checkAndUpdate(Question to, Question from) throws ConstraintBreakException {
		if(validate(from)) {
			setValues(to, from);
			questionDAO.save(to);
		}
		else 
			throw new ConstraintBreakException("El número de respuestas es incorrecto (1-" + maxAnswers + ") y solo debe existir una correcta.");
	}
	
	@Override
	public Question getAndCheck(Integer idQuestion) throws NotFoundException {
		Optional<Question> question = findById(idQuestion);
		question.orElseThrow(() -> new NotFoundException("La pregunta no existe"));
		return question.get();
	}
	
	private boolean validate(Question question) {
		if(question.getAnswer().size() > 0 && question.getAnswer().size() <= maxAnswers)
			return question.getAnswer().stream().filter(a -> a.getCorrect() != null && a.getCorrect()).count() == 1;
		return false;
	}
	
	private void deleteAnswers(Question q) {
		if(q.getAnswer() != null) {
			for(Answer a : q.getAnswer()) {
				answerService.delete(a);
			}
			q.setAnswer(new ArrayList<Answer>());
		}
	}
	
	private void addNewsAnswers(Question q) {
		if(q.getAnswer() != null)
			for(Answer a : q.getAnswer())
				a = answerService.create(a);	
	}
	
	private void updateAnswers(Question to, Question from) {
		deleteAnswers(to);
		if(from.getAnswer() != null)
			for(Answer a : from.getAnswer())
				to.getAnswer().add(answerService.create(a));	
	}
	
	@Override
	public void deleteAll(Question q) {
		deleteAnswers(q);
		questionDAO.delete(q);
	}
}
