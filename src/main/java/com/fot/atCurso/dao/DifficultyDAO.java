package com.fot.atCurso.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.fot.atCurso.model.Course;
import com.fot.atCurso.model.Difficulty;

@Repository
public interface DifficultyDAO extends AbstractDAO<Difficulty>{

}
