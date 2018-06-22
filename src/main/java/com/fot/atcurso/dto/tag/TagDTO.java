package com.fot.atcurso.dto.tag;

import java.io.Serializable;

import lombok.Data;

@Data
public class TagDTO implements Serializable {
	
	private static final long serialVersionUID = 189798678L;
	
	private Integer idTag;
	private String name;
}
