package com.fot.atCurso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.fot.atCurso.service.user.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
}
