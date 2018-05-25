package com.fot.atCurso.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Selection {
	
	public static final String FIELD_IDUSER = "idUser";
	public static final String FIELD_IDQUESTIONARY = "idQuestionary";
	public static final String FIELD_IDANSWER = "idAnswer";
	public static final String FIELD_IDQUESTION = "idQuestion";

	@Id
	@GeneratedValue
	private Integer idSelection;
	
	@JoinColumn(name = FIELD_IDUSER)
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	
	@JoinColumn(name = FIELD_IDQUESTIONARY)
	@ManyToOne(fetch = FetchType.LAZY)
	private Questionary questionary;
	
	@JoinColumn(name = FIELD_IDQUESTION)
	@ManyToOne(fetch = FetchType.LAZY)
	private Question question;
	
	@JoinColumn(name = FIELD_IDANSWER)
	@ManyToOne(fetch = FetchType.LAZY)
	private Answer answer;
}
