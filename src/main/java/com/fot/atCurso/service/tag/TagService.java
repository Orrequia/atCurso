package com.fot.atCurso.service.tag;

import com.fot.atCurso.model.Tag;
import com.fot.atCurso.service.AbstractService;

public interface TagService extends AbstractService<Tag, Integer> {
	 
	boolean isEqual(Tag t1, Tag t2);
	void setValues(Tag to, Tag from);
}
