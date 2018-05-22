package com.fot.atCurso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.fot.atCurso.model.User;
import com.fot.atCurso.service.AbstractService;
import com.fot.atCurso.service.user.UserService;
 
@Controller
public class UserController {

	@Autowired
	AbstractService<User, Integer> userService;
}
