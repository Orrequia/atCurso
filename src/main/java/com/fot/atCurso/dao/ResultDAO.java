package com.fot.atCurso.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.fot.atCurso.model.Result;

@Repository
public interface ResultDAO extends PagingAndSortingRepository<Result, Integer>{
		
}