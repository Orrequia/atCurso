package com.fot.atCurso.service.course;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fot.atCurso.component.dates.OperationDates;
import com.fot.atCurso.dao.CourseDAO;
import com.fot.atCurso.exception.NotFoundException;
import com.fot.atCurso.model.Course;
import com.fot.atCurso.model.Quiz;
import com.fot.atCurso.model.User;
import com.fot.atCurso.service.AbstractServiceImpl;
import com.fot.atCurso.service.quiz.QuizService;
import com.fot.atCurso.service.user.UserService;

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
	public boolean isEqual(Course c1, Course c2) {
		return c1.getName().equals( c2.getName()) &&
				operationDates.compare(c1.getStart_date(), c2.getStart_date()) &&
				operationDates.compare(c1.getEnding_date(), c2.getEnding_date()) &&
				c1.getUser().equals(c2.getUser()) &&
				c1.getQuiz().equals(c2.getQuiz());		
	}
	
	@Override
	public void setValues(Course to, Course from) {
		to.setName(from.getName());
		to.setStart_date(from.getStart_date());
		to.setEnding_date(from.getEnding_date());
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
		course.orElseThrow(() -> new NotFoundException("El curso no existe"));
		return course.get();
	}
}