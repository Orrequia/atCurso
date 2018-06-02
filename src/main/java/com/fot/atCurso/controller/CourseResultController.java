package com.fot.atCurso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fot.atCurso.component.mapper.result.ResultMapper;
import com.fot.atCurso.dto.result.ResultDTO;
import com.fot.atCurso.exception.NotFoundException;
import com.fot.atCurso.exception.ParametersNotAllowedException;
import com.fot.atCurso.model.Result;
import com.fot.atCurso.service.result.ResultService;

@RestController
@RequestMapping(value= "/course/{idCourse}/result")
public class CourseResultController {
	
	@Autowired
	ResultService resultService;
	
	@Autowired
	ResultMapper resultMapper;
	
	@GetMapping
	public List<ResultDTO> findAll(@RequestParam(defaultValue = "0", required= false ) Integer page, 
							 @RequestParam(defaultValue = "10", required= false ) Integer size,
							 @PathVariable("idCourse") Integer idCourse) throws ParametersNotAllowedException, NotFoundException {
		final List<Result> results = resultService.findResultByCourse(idCourse, PageRequest.of(page, size));
		return resultMapper.modelToDto(results);
	}
	
	@GetMapping("/{idResult}")
	public ResultDTO findById(@PathVariable("idCourse") Integer idCourse,
			 @PathVariable("idResult") Integer idResult) throws NotFoundException {
		final Result result = resultService.findOneResultByCourse(idCourse, idResult);
		return resultMapper.modelToDto(result);
	}
}
