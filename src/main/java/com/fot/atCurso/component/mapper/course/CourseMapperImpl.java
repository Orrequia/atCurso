package com.fot.atCurso.component.mapper.course;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fot.atCurso.component.mapper.AbstractMapper;
import com.fot.atCurso.dto.course.CourseDTO;
import com.fot.atCurso.exception.NotFoundException;
import com.fot.atCurso.model.Course;
import com.fot.atCurso.model.Quiz;
import com.fot.atCurso.model.User;
import com.fot.atCurso.service.quiz.QuizService;
import com.fot.atCurso.service.user.UserService;

@Component
public class CourseMapperImpl extends AbstractMapper<Course, CourseDTO> implements CourseMapper {
	
	@Autowired
	UserService userService;
	
	@Autowired
	QuizService quizService;
	
	@Override
	public Class<? extends CourseDTO> dtoClazz() {
		return CourseDTO.class;
	}

	@Override
	public Class<? extends Course> modelClazz() {
		return Course.class;
	}
	
	@Override
	public Course dtoToModel(CourseDTO dto) throws NotFoundException {
		List<User> user = integerToUser(dto.getUsers());
		List<Quiz> quiz = integerToQuiz(dto.getQuizzes());
		return map(dto, user, quiz);
	}
	
	@Override
	public CourseDTO modelToDto(Course model) {
		List<Integer> users = userToInteger(model.getUser());
		List<Integer> quizzes = quizToInteger(model.getQuiz());
		return map(model, users, quizzes);
	}
	
	private List<User> integerToUser(List<Integer> users) throws NotFoundException {
		if(users != null) {
			List<Optional<User>> user = users.stream().map(iU -> userService.findById(iU)).collect(Collectors.toList());
			for(Optional<User> u: user) 
				u.orElseThrow(() -> new NotFoundException("Algunos o todos los usuarios no existen"));
			return user.stream().map(Optional::get).collect(Collectors.toList());
		}
		return new ArrayList<User>();
	}
	
	private List<Integer> userToInteger(List<User> user) {
		return user.stream().map(u -> u.getIdUser()).collect(Collectors.toList());
	}
	
	private List<Quiz> integerToQuiz(List<Integer> quizzes) throws NotFoundException {
		if(quizzes != null) {
			List<Optional<Quiz>> quiz = quizzes.stream().map(iQ -> quizService.findById(iQ)).collect(Collectors.toList());
			for(Optional<Quiz> q: quiz) 
				q.orElseThrow(() -> new NotFoundException("Algunos o todos los cuestionarios no existen"));
			return quiz.stream().map(Optional::get).collect(Collectors.toList());
		}
		return new ArrayList<Quiz>();
	}
	
	private List<Integer> quizToInteger(List<Quiz> quiz) {
		return quiz.stream().map(q -> q.getIdQuiz()).collect(Collectors.toList());
	}
	
	private Course map(CourseDTO dto, List<User> user, List<Quiz> quiz) {
		Course course = dozer.map(dto, modelClazz());
		course.setUser(user);
		course.setQuiz(quiz);
		return course;
	}
	
	private CourseDTO map(Course model, List<Integer> users, List<Integer> quizzes) {
		CourseDTO dto = dozer.map(model, dtoClazz());
		dto.setQuizzes(quizzes);
		dto.setUsers(users);
		return dto;
	}
}
