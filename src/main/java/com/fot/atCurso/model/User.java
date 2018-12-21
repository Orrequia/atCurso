package com.fot.atCurso.model;

import javax.persistence.*;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "t_user")
public class User {

	public static final String FIELD_ID_USER = "idUser";
	public static final String FIELD_ID_ROLE = "idRole";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idUser;
	
	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String email;

	@Column(unique = true, nullable = false)
	private String username;

	@Column
	private String password;

	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.REMOVE)
	@JoinColumn(name= FIELD_ID_USER, referencedColumnName= FIELD_ID_USER)
	private List<Result> result;

	@JoinColumn(name = FIELD_ID_ROLE, nullable=false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Role role;
}

