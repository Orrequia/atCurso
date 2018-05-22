package com.fot.atCurso.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.fot.atCurso.model.Question;

@Repository
public interface QuestionDAO extends PagingAndSortingRepository<Question, Integer>{

}