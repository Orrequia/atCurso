package com.fot.atCurso.dto.course;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class CourseDTO implements Serializable {

	private static final long serialVersionUID = 866461L;
	
	private Integer idCourse;
	private String name;
	private Date start_date;
	private Date ending_date;
	private List<Integer> users;
	private List<Integer> quizzes;
}
