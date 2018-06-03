package com.fot.atCurso.dto.user;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class UserDTO implements Serializable {

	private static final long serialVersionUID = 98798681L;
	
	private Integer idUser;
	private String name;
	private String email;
	private List<Integer> results;
}
