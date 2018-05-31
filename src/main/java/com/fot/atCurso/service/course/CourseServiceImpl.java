package com.fot.atCurso.service.course;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fot.atCurso.dao.CourseDAO;
import com.fot.atCurso.exceptions.NotFoundException;
import com.fot.atCurso.model.Course;
import com.fot.atCurso.model.Quiz;
import com.fot.atCurso.model.Result;
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
	
	@Override
	public boolean isEqual(Course c1, Course c2) {
		return c1.getName().equals( c2.getName()) &&
				c1.getStart_date().equals(c2.getStart_date()) &&
				c1.getEnding_date().equals(c2.getEnding_date()) &&
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
	public Optional<User> searchUser(Course course, Integer idUser) {
		return course.getUser().stream().filter(u -> u.getIdUser() == idUser).findFirst();
	}
}
