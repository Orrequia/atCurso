package com.fot.atcurso.dto.quiz;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fot.atcurso.enums.ModalityEnum;

import lombok.Data;

@Data
public class QuizDTO implements Serializable {

	private static final long serialVersionUID = 1987875L;
	
	private Integer idQuiz;
	private String name;
	private ModalityEnum modality;
	private Date deliveryTime;
	private List<Integer> tags;
	private List<Integer> questions;
}
