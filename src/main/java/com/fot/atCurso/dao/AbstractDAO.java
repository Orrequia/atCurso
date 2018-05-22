package com.fot.atCurso.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.fot.atCurso.model.Answer;

public interface AbstractDAO<T> extends PagingAndSortingRepository<T, Integer> {

}
