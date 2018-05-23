package com.fot.atCurso.service.tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fot.atCurso.dao.TagDAO;
import com.fot.atCurso.model.Tag;
import com.fot.atCurso.service.AbstractServiceImpl;

@Service
public class TagServiceImpl extends AbstractServiceImpl<Tag, TagDAO> implements TagService {

	@Autowired
	TagDAO tagDAO;
}
