package com.fot.atCurso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.fot.atCurso.service.question.QuestionService;

@Controller
public class QuestionController {

	@Autowired
	QuestionService questionService;
}