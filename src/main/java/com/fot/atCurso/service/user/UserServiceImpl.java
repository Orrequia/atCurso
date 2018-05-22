package com.fot.atCurso.service.user;

import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fot.atCurso.dao.UserDAO;
import com.fot.atCurso.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDAO;

	@Override
	public User create(User t) {
		return userDAO.save(t);
	}

	@Override
	public void update(User t) {
		userDAO.save(t);
	}

	@Override
	public Optional<User> findById(Integer id) {
		return userDAO.findById(id);
	}

	@Override
	public Set<User> findAll(Pageable p) {
		return userDAO.findAll(PageRequest.of(p.getPageNumber(), p.getPageSize())).stream().collect(Collectors.toSet());
	}

	@Override
	public void delete(User t) {
		userDAO.delete(t);
	}
}
