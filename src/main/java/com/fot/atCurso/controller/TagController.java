package com.fot.atCurso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.fot.atCurso.service.tag.TagService;

@Controller
public class TagController {

	@Autowired
	TagService tagService;
}
