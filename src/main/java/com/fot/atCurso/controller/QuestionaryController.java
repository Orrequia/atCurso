package com.fot.atCurso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.fot.atCurso.service.questionary.QuestionaryService;

@Controller
public class QuestionaryController {

	@Autowired
	QuestionaryService questionaryService;
}