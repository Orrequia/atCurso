package com.fot.atCurso.service.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fot.atCurso.dao.UserDAO;
import com.fot.atCurso.exceptions.UniqueValueViolationException;
import com.fot.atCurso.model.User;
import com.fot.atCurso.service.AbstractServiceImpl;

@Service
public class UserServiceImpl extends AbstractServiceImpl<User, UserDAO> implements UserService {
	
	@Autowired
	UserDAO userDAO;

	@Override
	public boolean isEqual(User u1, User u2) {
		return u1.getName().equals( u2.getName()) &&
				u1.getEmail().equals(u2.getEmail());
	}
	
	@Override
	public void setValues(User to, User from) throws UniqueValueViolationException {
		if(from.getName() != null) to.setName(from.getName());
		if(from.getEmail() != null) to.setEmail(from.getEmail());
		if(from.getPassword() != null) to.setPassword(from.getPassword());
	}
	
	@Override
	public Optional<User> findByEmail(String email) {
		return userDAO.findOneByEmail(email);
	}
}
