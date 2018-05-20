package com.fot.atCurso.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Course {
	
	public static final String FIELD_IDCOURSE = "idCourse";
	public static final String FIELD_USER = "user";
	
	@Id
	@GeneratedValue
	private Integer idCourse;
	
	@Column(nullable = false)
	private String name;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha_inicio;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha_fin;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="user_course", 
	joinColumns=@JoinColumn(name=FIELD_IDCOURSE, referencedColumnName=FIELD_IDCOURSE),
	inverseJoinColumns=@JoinColumn(name=User.FIELD_IDUSER, referencedColumnName=User.FIELD_IDUSER))
	private List<User> user;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = Questionary.FIELD_COURSE)
	private List<Questionary> questionary;
}
