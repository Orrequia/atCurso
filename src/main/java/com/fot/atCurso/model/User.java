package com.fot.atCurso.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;

import java.util.List;

import javax.persistence.Column;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User {

	public static final String FIELD_IDUSER = "idUser";
	
	@Id
	@GeneratedValue
	private Integer idUser;
	
	@Column(nullable = false)
	private String name;

	@Column(unique = true, nullable = false)
	private String email;
	
	private String password;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = Result.FIELD_USER)
	private List<Result> result;
	
	@ManyToMany(fetch=FetchType.LAZY, mappedBy = Course.FIELD_USER)
	private List<Course> course;
}

