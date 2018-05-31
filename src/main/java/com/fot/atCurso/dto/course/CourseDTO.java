package com.fot.atCurso.dto.course;

import java.util.Date;
import java.util.List;

import com.fot.atCurso.model.Quiz;
import com.fot.atCurso.model.User;

import lombok.Data;

@Data
public class CourseDTO {

	private Integer idCourse;
	private String name;
	private Date fecha_inicio;
	private Date fecha_fin;
	private List<Integer> users;
	private List<Integer> quizzes;
}
