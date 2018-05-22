package com.fot.atCurso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.fot.atCurso.service.result.ResultService;

@Controller
public class ResultController {

	@Autowired
	ResultService resultService;
}