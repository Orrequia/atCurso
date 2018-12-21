package com.fot.atCurso.service.user;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import com.fot.atCurso.model.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fot.atCurso.dao.UserDAO;
import com.fot.atCurso.exception.ConstraintBreakException;
import com.fot.atCurso.exception.NotFoundException;
import com.fot.atCurso.service.AbstractServiceImpl;
import com.fot.atCurso.service.course.CourseService;
import com.fot.atCurso.service.selection.SelectionService;

@Service
public class UserServiceImpl extends AbstractServiceImpl<User, UserDAO> implements UserService {

	private final UserDAO userDAO;
	private CourseService courseService;
	private SelectionService selectionService;

	@Autowired
	public UserServiceImpl(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Autowired
	public void setService(CourseService courseService) {
		this.courseService = courseService;
	}

	@Autowired
	public void setService(SelectionService selectionService) {
		this.selectionService = selectionService;
	}

	@Override
	public boolean isEqual(User u1, User u2) {
		return StringUtils.equals(u1.getName(), u2.getName()) &&
				StringUtils.equals(u1.getEmail(), u2.getEmail()) &&
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
	public void removeAllSelections(User user) {
		selectionService.deleteByUser(user);
	}

	@Override
	public List<User> findUserByCourse(Integer idCourse, Pageable p) throws NotFoundException {
		courseService.getAndCheck(idCourse);
		return userDAO.findByCourse(idCourse, PageRequest.of(p.getPageNumber(), p.getPageSize()));
	}

	@Override
	public User findOneUserByCourse(Integer idCourse, Integer idUser) throws NotFoundException {
		final Course course = courseService.getAndCheck(idCourse);
		return getAndCheckBelongCourse(course, idUser);
	}


	@Override
	public User getAndCheck(Integer idUser) throws NotFoundException {
		Optional<User> user = findById(idUser);
		user.orElseThrow(() -> new NotFoundException("El usuario no existe"));
		return user.get();
	}

	@Override
	public User getAndCheckBelongCourse(Course course, Integer idUser) throws NotFoundException {
		final Optional<User> user = course.getUser().stream().filter(u -> u.getIdUser().equals(idUser)).findFirst();
		user.orElseThrow(() -> new NotFoundException("Este usuario no pertenece a este curso"));
		return user.get();
	}

	@Override
	public void checkValues(User user) throws ConstraintBreakException {
		String pattern = "[^@]+@[^@]+\\.[a-zA-Z]{2,}";
		if(!Pattern.matches(pattern, user.getEmail()))
			throw new ConstraintBreakException("El correo tiene un formato incorrecto");
	}

}
