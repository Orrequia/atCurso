package com.fot.atCurso.dto.result;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class ResultDTO {

	private Integer idResult;
	private Date date;
	private Float score;
	private Integer quiz;
}
