package com.fot.atCurso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.fot.atCurso.service.difficulty.DifficultyService;

@Controller
public class DifficultyController {

	@Autowired
	DifficultyService difficultyService;
}