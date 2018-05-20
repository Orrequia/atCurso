package com.fot.atCurso.service.user;

import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.SocketUtils;

import com.fot.atCurso.dao.UserDAO;
import com.fot.atCurso.model.User;

@Service
public class UserServiceImpl implements UserService, InitializingBean {

	@Autowired
	UserDAO userDAO;

	@Override
	public void test() {
		final User u = new User();
		u.setEmail("asd@g.com");
		u.setName("Pepe 1");
		u.setPassword("pepe123");
		userDAO.save(u);
		final Optional<User> user = userDAO.findOneByNameOrderByIdUserDesc("Pepe 1");
		System.out.println("_________________");
		System.out.println(user.isPresent() ? user.get().getName() : "no encontrado");
		System.out.println("_________________");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		test();
	}

}
