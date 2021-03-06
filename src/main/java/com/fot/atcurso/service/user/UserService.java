package com.fot.atcurso.service.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.fot.atcurso.exception.ConstraintBreakException;
import com.fot.atcurso.exception.NotFoundException;
import com.fot.atcurso.model.Course;
import com.fot.atcurso.model.Result;
import com.fot.atcurso.model.User;
import com.fot.atcurso.service.AbstractService;

public interface UserService extends AbstractService<User, Integer> {
	
	List<User> findUserByCourse(Integer idCourse, Pageable p) throws NotFoundException;
	User findOneUserByCourse(Integer idCourse, Integer idUser) throws NotFoundException;
	
	boolean isEqual(User u1, User u2);
	void setValues(User to, User from);
	
	Optional<User> findByEmail(String email);
	
	void addResult(User user, Result result);
	
	void removeResult(User user, Result result);
	
	void removeAllSelections(User user);
	
	User getAndCheck(Integer idUser) throws NotFoundException;
	User getAndCheckBelongCourse(Course course, Integer idUser) throws NotFoundException;
	
	void checkValues(User user) throws ConstraintBreakException;
}
