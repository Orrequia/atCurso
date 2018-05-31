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
	public Set<User> findCourseUsers(Pageable p, Integer idCourse) throws NotFoundException {
		Optional<Course> course = courseDAO.findById(idCourse);
		course.orElseThrow(() -> new NotFoundException("El curso no existe."));
		List<User> users = course.get().getUser();
		return new PageImpl<User>(users, PageRequest.of(p.getPageNumber(), p.getPageSize()), users.size()).stream().collect(Collectors.toSet());
	}
	
	@Override
	public Set<Quiz> findCourseQuestionaries(Pageable p, Integer idCourse) throws NotFoundException {
		Optional<Course> course = courseDAO.findById(idCourse);
		course.orElseThrow(() -> new NotFoundException("El curso no existe."));
		List<Quiz> questionaries = course.get().getQuiz();
		return new PageImpl<Quiz>(questionaries, PageRequest.of(p.getPageNumber(), p.getPageSize()), questionaries.size()).stream().collect(Collectors.toSet());
	}
}
