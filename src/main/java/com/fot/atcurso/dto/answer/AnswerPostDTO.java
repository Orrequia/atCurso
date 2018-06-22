package com.fot.atcurso.dto.answer;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class AnswerPostDTO extends AnswerDTO {

	private static final long serialVersionUID = 65945365L;
	
	private Boolean correct;
}
