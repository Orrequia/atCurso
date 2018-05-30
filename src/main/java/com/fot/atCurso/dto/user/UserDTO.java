package com.fot.atCurso.dto.user;

import java.util.List;

import lombok.Data;

@Data
public class UserDTO {

	private Integer idUser;
	private String name;
	private String email;
	private List<Integer> results;
}
