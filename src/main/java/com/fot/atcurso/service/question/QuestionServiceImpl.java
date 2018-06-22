package com.fot.atcurso.service.question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fot.atcurso.dao.QuestionDAO;
import com.fot.atcurso.enums.ModalityEnum;
import com.fot.atcurso.exception.RequirementsNotMetException;
import com.fot.atcurso.exception.AlreadyDoneException;
import com.fot.atcurso.exception.ConstraintBreakException;
import com.fot.atcurso.exception.NotFoundException;
import com.fot.atcurso.model.Answer;
import com.fot.atcurso.model.Course;
import com.fot.atcurso.model.Question;
import com.fot.atcurso.model.Quiz;
import com.fot.atcurso.model.Selection;
import com.fot.atcurso.model.Tag;
import com.fot.atcurso.model.User;
import com.fot.atcurso.service.AbstractServiceImpl;
import com.fot.atcurso.service.answer.AnswerService;
import com.fot.atcurso.service.course.CourseService;
import com.fot.atcurso.service.quiz.QuizService;
import com.fot.atcurso.service.selection.SelectionService;
import com.fot.atcurso.service.tag.TagService;
import com.fot.atcurso.service.user.UserService;

@Service
public class QuestionServiceImpl extends AbstractServiceImpl<Question, QuestionDAO> implements QuestionService {
	
	private static final Integer MAXANSWERS = Integer.valueOf(4);
	
	@Autowired
	QuestionDAO questionDAO;
	
	@Autowired
	AnswerService answerService;
	
	@Autowired
	TagService tagService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	QuizService quizService;
	
	@Autowired
	CourseService courseService;
	
	@Autowired
	SelectionService selectionService;
	
	@Override
	public List<Question> findByTag(Integer idTag, Pageable p) throws NotFoundException {
		Tag tag = tagService.getAndCheck(idTag);
		return questionDAO.findByTag(tag, p);
	}
	
	@Override
	public boolean isEqual(Question q1, Question q2) {
		return StringUtils.equals(q1.getName(), q2.getName()) &&
				q1.getTag().equals(q2.getTag()) &&
				q1.getDifficulty().equals(q2.getDifficulty()) &&
				CollectionUtils.isEqualCollection(getStringsAnswer(q1.getAnswer()),
												  getStringsAnswer(q2.getAnswer()));
	}
	
	@Override 
	public void setValues(Question to, Question from) {
		to.setName(from.getName());
		to.setTag(from.getTag());
		to.setDifficulty(from.getDifficulty());
		updateAnswers(to, from);
	}
	
	@Override
	public void deleteAll(Question q) {
		deleteAnswers(q);
		questionDAO.delete(q);
	}
	
	@Override
	public Question checkAndCreate(Question question) throws ConstraintBreakException {
		if(validate(question)) {
			addNewsAnswers(question);
			return questionDAO.save(question);
		}
		throw new ConstraintBreakException("El número de respuestas es incorrecto (1-" + MAXANSWERS + ") y solo debe existir una correcta.");
	}
	
	@Override
	public void checkAndUpdate(Question to, Question from) throws ConstraintBreakException {
		if(validate(from)) {
			setValues(to, from);
			questionDAO.save(to);
		}
		else 
			throw new ConstraintBreakException("El número de respuestas es incorrecto (1-" + MAXANSWERS + ") y solo debe existir una correcta.");
	}
	
	@Override
	public Question getAndCheck(Integer idQuestion) throws NotFoundException {
		Optional<Question> question = findById(idQuestion);
		return question.orElseThrow(() -> new NotFoundException("La pregunta no existe"));
	}
	
	@Override
	public Question getAndCheckBelongQuiz(Quiz quiz, Integer idQuestion) throws NotFoundException {
		final Optional<Question> question = quiz.getQuestion().stream().filter(q -> q.getIdQuestion() == idQuestion).findFirst();
		return question.orElseThrow(() -> new NotFoundException("Esta pregunta no pertenece a este cuestionario"));
	}
	
	@Override
	public List<Question> getAndCheckQuestions(Integer idUser, Integer idQuiz) throws NotFoundException, RequirementsNotMetException, AlreadyDoneException {
		checkConditionsUserAndQuiz(idUser, idQuiz);
		Quiz quiz = quizService.getAndCheck(idQuiz);
		User user = userService.getAndCheck(idUser);
		if(quiz.getModality() == ModalityEnum.ALLINONE) return getAllQuestions(user, quiz); 
		else return Collections.singletonList(getOneQuestion(user, quiz));
	}
	
	@Override
	public void checkConditionsUserAndQuiz(Integer idUser, Integer idQuiz) throws NotFoundException {
		Optional<Course> course = courseService.findByQuiz(idQuiz);
		userService.getAndCheckBelongCourse(course.orElseThrow(() -> new NotFoundException("El cuestionario no existe")) , idUser);
	}
	
	@Override
	public List<Question> findByTags(List<Tag> tags) {
		return questionDAO.findByTagIn(tags);
	}
	
	private List<Question> getAllQuestions(User user, Quiz quiz) throws AlreadyDoneException {
		List<Question> questions = quiz.getQuestion();
		if(selectionService.isFirstTime(user, quiz))
			selectionService.create(user, quiz, questions);
		else throw new AlreadyDoneException("Ya has iniciado el cuestionario, respondelas las preguntas o revisa tu expediente");
		Collections.shuffle(questions);
		return questions;
	}

	private Question getOneQuestion(User user, Quiz quiz) throws RequirementsNotMetException, AlreadyDoneException {
		List<Question> questions = quiz.getQuestion();
		Collections.shuffle(questions);
		if(selectionService.isFirstTime(user, quiz)) {
			selectionService.create(user, quiz, questions.get(0));
			return questions.get(0);
		}
		else {
			List<Selection> selections = selectionService.findByUserAndQuiz(user, quiz);
			if(selections.get(0).getRespondedDate() == null)
				throw new RequirementsNotMetException("No puedes obtener una nueva pregunta si no has respondido la anterior");
			if(selectionService.allQuestionsBeenAnswered(user, quiz)) 
				throw new AlreadyDoneException("Ya has realizado este cuestionario, revisa tu expediente");
			return getOtherQuestion(user, quiz, selections);
		}
	}
	
	private Question getOtherQuestion(User user, Quiz quiz, List<Selection> selections) throws AlreadyDoneException {
		List<Question> questions = quiz.getQuestion();
		for(Question q : questions) {
			int i = 0;
			boolean found = false;
			while(!found && i < selections.size()) {
				if(StringUtils.equals(q.getName(), selections.get(i).getQuestion())) found = true;
				i++;
			}
			if(!found) {
				selectionService.create(user, quiz, q);
				return q;
			}
		}
		throw new AlreadyDoneException("Ya no quedan mas preguntas, has finalizado el cuestionario, revisa tu expediente.");
	}

	private boolean validate(Question question) {
		if(!question.getAnswer().isEmpty() && question.getAnswer().size() <= MAXANSWERS)
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
			q.getAnswer().stream().map(answerService::create).collect(Collectors.toList());
	}
	
	private void updateAnswers(Question to, Question from) {
		deleteAnswers(to);
		if(from.getAnswer() != null)
			for(Answer a : from.getAnswer())
				to.getAnswer().add(answerService.create(a));	
	}
	
	private List<String> getStringsAnswer(List<Answer> answers) {
		List<String> sAnswers = new ArrayList<>();
		for(Answer a : answers) 
			sAnswers.add(a.getName());
		return sAnswers;
	}
}
