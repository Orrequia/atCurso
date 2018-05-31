package com.fot.atCurso.component.mapper.tag;

import com.fot.atCurso.component.mapper.AbstractMapper;
import com.fot.atCurso.dto.tag.TagDTO;
import com.fot.atCurso.model.Tag;

public class TagMapperImpl extends AbstractMapper<Tag, TagDTO> implements TagMapper {
	
	@Override
	public Class<? extends TagDTO> dtoClazz() {
		return TagDTO.class;
	}
	
	@Override
	public Class<? extends Tag> modelClazz() {
		return Tag.class;
	}
}
