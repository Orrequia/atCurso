package com.fot.atCurso.dto.course;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class CourseDTO {

	private Integer idCourse;
	private String name;
	private Date start_date;
	private Date ending_date;
	private List<Integer> users;
	private List<Integer> quizzes;
}
