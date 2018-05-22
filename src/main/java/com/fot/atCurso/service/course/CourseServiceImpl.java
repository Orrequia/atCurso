package com.fot.atCurso.service.course;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fot.atCurso.dao.CourseDAO;
import com.fot.atCurso.model.Course;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	CourseDAO courseDAO;
	
	@Override
	public Course create(Course t) {
		return courseDAO.save(t);
	}

	@Override
	public void update(Course t) {
		courseDAO.save(t);
	}

	@Override
	public Optional<Course> findById(Integer id) {
		return courseDAO.findById(id);
	}

	@Override
	public Set<Course> findAll(Pageable p) {
		return courseDAO.findAll(PageRequest.of(p.getPageNumber(), p.getPageSize())).stream().collect(Collectors.toSet());
	}

	@Override
	public void delete(Course t) {
		courseDAO.delete(t);
	}
}
