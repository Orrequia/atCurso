package com.fot.atCurso.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.fot.atCurso.model.Answer;
import com.fot.atCurso.service.AbstractService;

public class AnswerController {

	@Autowired
	AbstractService<Answer, Integer> abstractService;
}
