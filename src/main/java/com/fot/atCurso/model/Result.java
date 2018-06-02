package com.fot.atCurso.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Result {

	public static final String FIELD_IDQUIZ = "idQuiz";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idResult;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	private Float score;
	
	@JoinColumn(name = FIELD_IDQUIZ, nullable=false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Quiz quiz;
}
