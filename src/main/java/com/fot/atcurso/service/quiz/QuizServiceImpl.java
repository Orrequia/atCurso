package com.fot.atcurso.service.quiz;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fot.atcurso.dao.QuizDAO;
import com.fot.atcurso.exception.ConstraintBreakException;
import com.fot.atcurso.exception.NotFoundException;
import com.fot.atcurso.exception.UnequalObjectsException;
import com.fot.atcurso.model.Course;
import com.fot.atcurso.model.Question;
import com.fot.atcurso.model.Quiz;
import com.fot.atcurso.model.Tag;
import com.fot.atcurso.service.AbstractServiceImpl;
import com.fot.atcurso.service.course.CourseService;
import com.fot.atcurso.service.question.QuestionService;
import com.fot.atcurso.service.tag.TagService;

@Service
public class QuizServiceImpl extends AbstractServiceImpl<Quiz, QuizDAO> implements QuizService {

	@Autowired
	QuizDAO quizDAO;
	
	@Autowired
	TagService tagService;
	
	@Autowired
	CourseService courseService;
	
	@Autowired
	QuestionService questionService;
	
	@Override
	public boolean isEqual(Quiz q1, Quiz q2) {
		return StringUtils.equals(q1.getName(), q2.getName()) &&
				q1.getModality() == q2.getModality() &&
				q1.getQuestion().equals(q2.getQuestion()) &&
				q1.getTag().equals(q2.getTag());
	}
	
	@Override
	public void setValues(Quiz to, Quiz from) {
		to.setName(from.getName());
		to.setModality(from.getModality());
		to.setQuestion(from.getQuestion());
		to.setTag(from.getTag());
	}
	
	@Override
	public List<Quiz> findQuizByCourse(Integer idCourse, Pageable p) throws NotFoundException {
		courseService.getAndCheck(idCourse);
		return quizDAO.findByCourse(idCourse, PageRequest.of(p.getPageNumber(), p.getPageSize()));
	}
	
	@Override
	public Quiz findOneQuizByCourse(Integer idCourse, Integer idQuiz) throws NotFoundException {
		final Course course = courseService.getAndCheck(idCourse);
		return getAndCheckBelongCourse(course, idQuiz);
	}

	@Override
	public Quiz addToCourse(Integer idCourse, Quiz quiz) throws NotFoundException, ConstraintBreakException {
		final Course course = courseService.getAndCheck(idCourse);
		checkHaveTags(quiz);
		checkTagsQuestionsInTagsQuiz(quiz);
		final Quiz createQuiz = create(quiz);
		courseService.addQuiz(course, createQuiz);
		return createQuiz;
	}
	
	@Override
	public void updateToCourse(Integer idCourse, Integer idQuiz, Quiz newQuiz) throws NotFoundException, ConstraintBreakException {
		final Course course = courseService.getAndCheck(idCourse);
		final Quiz quiz = getAndCheckBelongCourse(course, idQuiz);
		checkHaveTags(quiz);
		checkTagsQuestionsInTagsQuiz(quiz);
		setValues(quiz, newQuiz);
		update(quiz);
	}
	
	@Override
	public void deleteToCourse(Integer idCourse, Integer idQuiz, Quiz bodyQuiz) throws NotFoundException, UnequalObjectsException {
		final Course course = courseService.getAndCheck(idCourse);
		final Quiz quiz = getAndCheckBelongCourse(course, idQuiz);
		if(!isEqual(bodyQuiz, quiz))
			throw new UnequalObjectsException("El cuestionario recibido no coincide con el almacenado");
		courseService.removeQuiz(course, quiz);
	}
	
	@Override
	public Quiz getAndCheck(Integer idQuiz) throws NotFoundException {
		final Optional<Quiz> quiz = quizDAO.findById(idQuiz);
		return quiz.orElseThrow(() -> new NotFoundException("El cuestionario no existe"));
	}
	
	@Override
	public Quiz getAndCheckBelongCourse(Course course, Integer idQuiz) throws NotFoundException {
		final Optional<Quiz> quiz = course.getQuiz().stream().filter(q -> q.getIdQuiz() == idQuiz).findFirst();
		return quiz.orElseThrow(() -> new NotFoundException("Este cuestionario no existe para este curso"));
	}
	
	@Override
	public void generateQuestions(Quiz quiz, Integer nQuestions) throws ConstraintBreakException {
		checkHaveTags(quiz);
		List<Question> questions = questionService.findByTags(quiz.getTag());
		if(questions.size() <= nQuestions) quiz.setQuestion(questions);
		else {
			Collections.shuffle(questions);
			quiz.setQuestion(questions.subList(0, nQuestions));
		}
	}
	
	private void checkHaveTags(Quiz quiz) throws ConstraintBreakException {
		if(quiz.getTag() == null || !quiz.getTag().isEmpty())
			throw new ConstraintBreakException("El cuestionario debe tener al menos un tag");
	}
	
	private void checkTagsQuestionsInTagsQuiz(Quiz quiz) throws NotFoundException {
		
		final List<Tag> tagQuestionList = quiz.getQuestion().stream().map(Question::getTag).collect(Collectors.toList());
		final List<Tag> tagQuizList = quiz.getTag();
		final Boolean tagQuestionContainSomeTagOfQuizTag = CollectionUtils.containsAny(tagQuestionList, tagQuizList);
		
		if(tagQuestionContainSomeTagOfQuizTag) throw new NotFoundException("El tag de alguna pregunta no pertenece a la lista de tags del cuestionario");	
	}
}
