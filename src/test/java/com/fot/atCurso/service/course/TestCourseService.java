package com.fot.atCurso.service.course;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.fot.atCurso.dao.CourseDAO;
import com.fot.atCurso.exception.NotFoundException;
import com.fot.atCurso.model.Course;
import com.fot.atCurso.model.Quiz;
import com.fot.atCurso.model.User;
import com.fot.atCurso.service.user.UserService;

@RunWith(MockitoJUnitRunner.class)
public class TestCourseService {
	
	private static final Integer IDCOURSE = 1;
	private static final String NAME = "AtSistemas Spring Mayo 2018";
	private static final Date START_DATE = new Date(1526248800L);
	private static final Date ENDING_DATE = new Date(1527717600L);
	List<User> USERS;
	List<Quiz> QUIZZES;
	Course course;
	
	private static final Integer IDUSER = 1;
	private static final String NAMEU = "Francisco Orrequia";
	
	private static final Integer IDQUIZ = 1;
	private static final String NAMEQ = "Cuestionario de GIT";
	
	@InjectMocks
	CourseService courseService = new CourseServiceImpl();
	
	@Mock
	CourseDAO courseDAO;
	
	@Mock
	UserService userService;
	
	@Before
	public void before() {
		USERS = new ArrayList<User>(); 
		QUIZZES = new ArrayList<Quiz>();
		course = new Course();
		course.setIdCourse(IDCOURSE);
		course.setName(NAME);
		course.setStart_date(START_DATE);
		course.setEnding_date(ENDING_DATE);
		course.setUser(USERS);
		course.setQuiz(QUIZZES);
	}
	
	@Test
	public void testCreate() {
		Mockito.when(courseDAO.save(course)).thenReturn(course);
		final Course res = courseService.create(course);
		
		Assert.assertEquals(IDCOURSE, res.getIdCourse());
		Assert.assertEquals(NAME, res.getName());
		Assert.assertEquals(START_DATE, res.getStart_date());
		Assert.assertEquals(ENDING_DATE, res.getEnding_date());
		Assert.assertEquals(USERS, res.getUser());
		Assert.assertEquals(QUIZZES, res.getQuiz());
	}
	
	@Test
	public void testFindByUser() throws NotFoundException {
		Pageable p = PageRequest.of(0, 10);
		User user = new User();
		user.setIdUser(IDUSER);
		user.setName(NAMEU);
		
		Mockito.when(userService.getAndCheck(user.getIdUser())).thenReturn(user);
		Mockito.when(courseDAO.findByUser(user, p)).thenReturn(Collections.singletonList(course));
		List<Course> courses = courseService.findByUser(user.getIdUser(), p);
		
		Assert.assertEquals(1, courses.size());
		Assert.assertEquals(IDCOURSE, courses.get(0).getIdCourse());
		Assert.assertEquals(NAME, courses.get(0).getName());
	}
	
	@Test
	public void testAddQuiz() {
		Quiz quiz = new Quiz();
		quiz.setIdQuiz(IDQUIZ);
		quiz.setName(NAMEQ);
		
		Mockito.when(courseDAO.save(course)).thenReturn(course);
		courseService.addQuiz(course, quiz);
		
		Assert.assertEquals(IDQUIZ, course.getQuiz().get(0).getIdQuiz());
		Assert.assertEquals(NAMEQ, course.getQuiz().get(0).getName());
	}

	@Test
	public void testRemoveQuiz() {
		Quiz quiz = new Quiz();
		quiz.setIdQuiz(IDQUIZ);
		quiz.setName(NAMEQ);
		course.getQuiz().add(quiz);
		
		Mockito.when(courseDAO.save(course)).thenReturn(course);
		courseService.removeQuiz(course, quiz);
		
		Assert.assertEquals(0, course.getQuiz().size());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
