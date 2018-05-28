package com.fot.atCurso.dto.course;

import java.util.Date;

import lombok.Data;

@Data
public class CourseDTO {

	private Integer idCourse;
	private String name;
	private Date fecha_inicio;
	private Date fecha_fin;
}
