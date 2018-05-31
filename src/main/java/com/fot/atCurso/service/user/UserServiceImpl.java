package com.fot.atCurso.service.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fot.atCurso.dao.UserDAO;
import com.fot.atCurso.model.User;
import com.fot.atCurso.service.AbstractServiceImpl;

@Service
public class UserServiceImpl extends AbstractServiceImpl<User, UserDAO> implements UserService {
	
	@Autowired
	UserDAO userDAO;

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
}
