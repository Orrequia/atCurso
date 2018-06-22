package com.fot.atcurso.dto.result;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class ResultDTO implements Serializable {

	private static final long serialVersionUID = 1987895677L;
	
	private Integer idResult;
	private Date date;
	private Float score;
	private Integer idQuiz;
}
