package com.fot.atCurso.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.fot.atCurso.model.Tag;

@Repository
public interface TagDAO extends PagingAndSortingRepository<Tag, Integer>{

}