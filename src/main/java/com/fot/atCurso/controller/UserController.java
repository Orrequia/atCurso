package com.fot.atCurso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.fot.atCurso.model.User;
import com.fot.atCurso.service.AbstractService;
 
@Controller
public class UserController {

	@Autowired
	AbstractService<User, Integer> userService;
}
