package com.fot.atCurso.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Selection {
	
	public static final String FIELD_IDUSER = "idUser";
	public static final String FIELD_IDQUIZ = "idQuiz";

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idSelection;
	
	@JoinColumn(name = FIELD_IDUSER, nullable=false)
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	
	@JoinColumn(name = FIELD_IDQUIZ, nullable=false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Quiz quiz;
	
	@Column(nullable = false)
	private String question;
	
	private String answer;
	
	private Boolean wasCorrect;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date askedDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date respondedDate;
}
