package com.fot.atcurso.service.tag;

import com.fot.atcurso.exception.NotFoundException;
import com.fot.atcurso.model.Tag;
import com.fot.atcurso.service.AbstractService;

public interface TagService extends AbstractService<Tag, Integer> {
	 
	boolean isEqual(Tag t1, Tag t2);
	void setValues(Tag to, Tag from);
	Tag getAndCheck(Integer idTag) throws NotFoundException;
}
