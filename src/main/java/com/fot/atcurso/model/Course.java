package com.fot.atcurso.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Course {
	
	public static final String FIELD_IDCOURSE = "idCourse";
	public static final String FIELD_USER = "user";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idCourse;
	
	@Column(nullable = false)
	private String name;
	
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Temporal(TemporalType.DATE)
	private Date endingDate;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="user_course", 
	joinColumns=@JoinColumn(name=FIELD_IDCOURSE, referencedColumnName=FIELD_IDCOURSE),
	inverseJoinColumns=@JoinColumn(name=User.FIELD_IDUSER, referencedColumnName=User.FIELD_IDUSER))
	private List<User> user;
	
	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.REMOVE)
	@JoinColumn(name=FIELD_IDCOURSE, referencedColumnName=FIELD_IDCOURSE)
	private List<Quiz> quiz;
}
