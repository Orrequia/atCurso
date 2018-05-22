package com.fot.atCurso.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.fot.atCurso.model.Answer;
import com.fot.atCurso.model.Course;

@Repository
public interface CourseDAO extends AbstractDAO<Course>{

}

