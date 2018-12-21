package com.fot.atCurso.service.answer;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fot.atCurso.dao.AnswerDAO;
import com.fot.atCurso.exception.AlreadyDoneException;
import com.fot.atCurso.exception.ExceededTimeException;
import com.fot.atCurso.exception.NotFoundException;
import com.fot.atCurso.model.Answer;
import com.fot.atCurso.model.Question;
import com.fot.atCurso.model.Quiz;
import com.fot.atCurso.model.Selection;
import com.fot.atCurso.model.User;
import com.fot.atCurso.service.AbstractServiceImpl;
import com.fot.atCurso.service.question.QuestionService;
import com.fot.atCurso.service.quiz.QuizService;
import com.fot.atCurso.service.result.ResultService;
import com.fot.atCurso.service.selection.SelectionService;
import com.fot.atCurso.service.user.UserService;

@Service
public class AnswerServiceImpl extends AbstractServiceImpl<Answer, AnswerDAO> implements AnswerService {

	private QuestionService questionService;
	private ResultService resultService;
	private UserService userService;
	private QuizService quizService;
	private SelectionService selectionService;


	@Autowired
	public void setService(QuizService quizService) {
		this.quizService = quizService;
	}

	@Autowired
	public void setService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setService(ResultService resultService) {
		this.resultService = resultService;
	}

	@Autowired
	public void setService(QuestionService questionService) {
		this.questionService = questionService;
	}

	@Autowired
	public void setService(SelectionService selectionService) {
		this.selectionService = selectionService;
	}

	@Override
	public Answer addAnswerToSelection(Integer idUser, Integer idQuiz, Integer idQuestion, Answer answer) throws NotFoundException, ExceededTimeException, AlreadyDoneException {
		questionService.checkConditionsUserAndQuiz(idUser, idQuiz);
		Quiz quiz = quizService.getAndCheck(idQuiz);
		User user = userService.getAndCheck(idUser);
		Question question = questionService.getAndCheckBelongQuiz(quiz, idQuestion);
		updateSelection(user, quiz, question, answer);
		return getCorrect(question);
	}
	
	private void updateSelection(User user, Quiz quiz, Question question, Answer answer) throws NotFoundException, ExceededTimeException, AlreadyDoneException {
		if(!selectionService.allQuestionsBeenAnswered(user, quiz)) {
			selectionService.answerTheQuestion(user, quiz, question, answer);
			if(selectionService.allQuestionsBeenAnswered(user, quiz))
				calculateResult(user, quiz, selectionService.findByUserAndQuiz(user, quiz));
		}
		else throw new AlreadyDoneException("Ya has realizado este cuestionario");
	}
	
	@Override
	public Answer getCorrect(Question question) {
		return question.getAnswer().stream().filter(a -> a.getCorrect() != null && a.getCorrect()).collect(Collectors.toList()).get(0);
	}
	
	private void calculateResult(User user, Quiz quiz, List<Selection> selects) throws NotFoundException {
		int numQuestion = selects.size();
		int corQuestion = selects.stream().filter(s -> s.getWasCorrect() != null && s.getWasCorrect()).collect(Collectors.toList()).size();
		resultService.create(user, quiz, ((float)corQuestion/numQuestion)*10);
	}
}
