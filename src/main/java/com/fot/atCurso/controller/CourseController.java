package com.fot.atCurso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.fot.atCurso.service.course.CourseService;

@Controller
public class CourseController {

	@Autowired
	CourseService courseService;
}
