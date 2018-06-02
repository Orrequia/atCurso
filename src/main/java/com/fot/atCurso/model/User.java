package com.fot.atCurso.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.List;

import javax.persistence.Column;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User {

	public static final String FIELD_IDUSER = "idUser";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idUser;
	
	@Column(nullable = false)
	private String name;

	@Column(unique = true, nullable = false)
	private String email;
	
	private String password;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name=FIELD_IDUSER, referencedColumnName=FIELD_IDUSER)
	private List<Result> result;	
}

