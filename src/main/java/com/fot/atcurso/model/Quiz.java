package com.fot.atcurso.model;


import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fot.atcurso.enums.ModalityEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Quiz {
	
	public static final String FIELD_IDTAG = "idTag";
	public static final String FIELD_IDQUESTIONARY = "idQuiz";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name=FIELD_IDQUESTIONARY)
	private Integer idQuiz;
	
	@Column(nullable = false)
	private String name;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ModalityEnum modality;
	
	@Temporal(TemporalType.TIME)
	private Date deliveryTime;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
      name="quiz_question",
      joinColumns=@JoinColumn(name=FIELD_IDQUESTIONARY, referencedColumnName=FIELD_IDQUESTIONARY),
      inverseJoinColumns=@JoinColumn(name=Question.FIELD_IDQUESTION, referencedColumnName=Question.FIELD_IDQUESTION))
	private List<Question> question;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
      name="quiz_tag",
      joinColumns=@JoinColumn(name=FIELD_IDQUESTIONARY, referencedColumnName=FIELD_IDQUESTIONARY, nullable=false),
      inverseJoinColumns=@JoinColumn(name=FIELD_IDTAG, referencedColumnName=FIELD_IDTAG, nullable=false))
	private List<Tag> tag;
}
