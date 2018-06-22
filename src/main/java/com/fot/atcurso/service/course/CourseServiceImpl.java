package com.fot.atcurso.service.course;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fot.atcurso.component.dates.OperationDates;
import com.fot.atcurso.dao.CourseDAO;
import com.fot.atcurso.exception.NotFoundException;
import com.fot.atcurso.model.Course;
import com.fot.atcurso.model.Quiz;
import com.fot.atcurso.model.User;
import com.fot.atcurso.service.AbstractServiceImpl;
import com.fot.atcurso.service.quiz.QuizService;
import com.fot.atcurso.service.user.UserService;

@Service
public class CourseServiceImpl extends AbstractServiceImpl<Course, CourseDAO> implements CourseService {

	@Autowired
	CourseDAO courseDAO;
	
	@Autowired
	QuizService quizService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	OperationDates operationDates;
	
	@Override
	public List<Course> findByUser(Integer idUser, Pageable p) throws NotFoundException {
		User user = userService.getAndCheck(idUser);
		return courseDAO.findByUser(user, p);
	}
	
	@Override
	public Optional<Course> findByQuiz(Integer idQuiz) throws NotFoundException {
		Quiz quiz = quizService.getAndCheck(idQuiz);
		return courseDAO.findOneByQuiz(quiz);
	}
	
	@Override
	public boolean isEqual(Course c1, Course c2) {
		return StringUtils.equals(c1.getName(), c2.getName()) &&
				operationDates.compare(c1.getStartDate(), c2.getStartDate()) &&
				operationDates.compare(c1.getEndingDate(), c2.getEndingDate()) &&
				c1.getUser().equals(c2.getUser()) &&
				c1.getQuiz().equals(c2.getQuiz());		
	}
	
	@Override
	public void setValues(Course to, Course from) {
		to.setName(from.getName());
		to.setStartDate(from.getStartDate());
		to.setEndingDate(from.getEndingDate());
		to.setUser(from.getUser());
		to.setQuiz(from.getQuiz());
	}
	
	@Override
	public void addQuiz(Course course, Quiz quiz) {
		course.getQuiz().add(quiz);
		courseDAO.save(course);
	}
	
	@Override
	public void removeQuiz(Course course, Quiz quiz) {
		course.getQuiz().remove(quiz);
		courseDAO.save(course);
	}
	
	@Override
	public Optional<User> searchUser(Course course, Integer idUser) {
		return course.getUser().stream().filter(u -> u.getIdUser() == idUser).findFirst();
	}
	
	@Override
	public Optional<Quiz> searchQuiz(Course course, Integer idQuiz) {
		return course.getQuiz().stream().filter(q -> q.getIdQuiz() == idQuiz).findFirst();
	}
	
	@Override
	public Course getAndCheck(Integer idCourse) throws NotFoundException {
		Optional<Course> course = findById(idCourse);
		return course.orElseThrow(() -> new NotFoundException("El curso no existe"));
	}
}