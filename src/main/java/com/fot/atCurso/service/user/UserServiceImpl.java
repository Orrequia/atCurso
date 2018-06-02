package com.fot.atCurso.service.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fot.atCurso.dao.CourseDAO;
import com.fot.atCurso.dao.UserDAO;
import com.fot.atCurso.exception.NotFoundException;
import com.fot.atCurso.exception.UnequalObjectsException;
import com.fot.atCurso.model.Result;
import com.fot.atCurso.model.User;
import com.fot.atCurso.model.Course;
import com.fot.atCurso.service.AbstractServiceImpl;
import com.fot.atCurso.service.course.CourseService;
import com.fot.atCurso.service.result.ResultService;

@Service
public class UserServiceImpl extends AbstractServiceImpl<User, UserDAO> implements UserService {
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	ResultService resultService;
	
	@Autowired
	CourseService courseService;

	@Override
	public boolean isEqual(User u1, User u2) {
		return u1.getName().equals( u2.getName()) &&
				u1.getEmail().equals(u2.getEmail()) &&
				u1.getResult().equals(u2.getResult());
	}
	
	@Override
	public void setValues(User to, User from) {
		to.setName(from.getName());
		to.setEmail(from.getEmail());
		to.setPassword(from.getPassword());
		to.setResult(from.getResult());
	}
	
	@Override
	public Optional<User> findByEmail(String email) {
		return userDAO.findOneByEmail(email);
	}
	
	@Override
	public void addResult(User user, Result result) {
		user.getResult().add(result);
		userDAO.save(user);
	}
	
	@Override
	public void removeResult(User user, Result result) {
		user.getResult().remove(result);
		userDAO.save(user);
	}
	
	@Override
	public List<User> findUserByCourse(Integer idCourse, Pageable p) throws NotFoundException {
		courseService.getAndCheck(idCourse);
		return userDAO.findByCourse(idCourse, PageRequest.of(p.getPageNumber(), p.getPageSize()));
	}
	
	@Override
	public User findOneUserByCourse(Integer idCourse, Integer idUser) throws NotFoundException {
		final Course course = courseService.getAndCheck(idCourse);
		final User user = getAndCheckBelongCourse(course, idUser);
		return user;
	}
	
	@Override
	public User getAndCheck(Integer idUser) throws NotFoundException {
		Optional<User> user = findById(idUser);
		user.orElseThrow(() -> new NotFoundException("El usuario no existe"));
		return user.get();
	}
	
	@Override
	public User getAndCheckBelongCourse(Course course, Integer idUser) throws NotFoundException {
		final Optional<User> user = course.getUser().stream().filter(u -> u.getIdUser() == idUser).findFirst();
		user.orElseThrow(() -> new NotFoundException("Este usuario no pertenece a este curso"));
		return user.get();
	}
}
