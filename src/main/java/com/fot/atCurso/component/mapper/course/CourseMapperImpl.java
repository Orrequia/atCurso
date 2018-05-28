package com.fot.atCurso.component.mapper.course;

import org.springframework.stereotype.Component;

import com.fot.atCurso.component.mapper.AbstractMapper;
import com.fot.atCurso.dto.course.CourseDTO;
import com.fot.atCurso.model.Course;

@Component
public class CourseMapperImpl extends AbstractMapper<Course, CourseDTO> implements CourseMapper {

	@Override
	public Class<? extends CourseDTO> dtoClazz() {
		return CourseDTO.class;
	}

	@Override
	public Class<? extends Course> modelClazz() {
		return Course.class;
	}
}
