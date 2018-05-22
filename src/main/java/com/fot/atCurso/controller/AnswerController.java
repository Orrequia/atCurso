package com.fot.atCurso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.fot.atCurso.service.answer.AnswerService;

@Controller
public class AnswerController {

	@Autowired
	AnswerService answerService;
}
