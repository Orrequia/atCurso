package com.fot.atCurso.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fot.atCurso.dao.UserDAO;
import com.fot.atCurso.model.User;
import com.fot.atCurso.service.AbstractServiceImpl;

@Service
public class UserServiceImpl extends AbstractServiceImpl<User, UserDAO> implements UserService {
	
	@Autowired
	UserDAO userDAO;
}
