package com.fot.atCurso.service.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fot.atCurso.dao.CourseDAO;
import com.fot.atCurso.model.Course;
import com.fot.atCurso.service.AbstractServiceImpl;

@Service
public class CourseServiceImpl extends AbstractServiceImpl<Course, CourseDAO> implements CourseService {

	@Autowired
	CourseDAO courseDAO;
}
