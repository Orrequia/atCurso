package com.fot.atCurso.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Result {

	public static final String FIELD_USER = "user";
	public static final String FIELD_IDQUESTIONARY = "idQuestionary";
	
	@Id
	@GeneratedValue
	private Integer idResult;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	@JoinColumn(name = FIELD_USER)
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	
	@JoinColumn(name = FIELD_IDQUESTIONARY)
	@ManyToOne(fetch = FetchType.LAZY)
	private Questionary questionary;
}
