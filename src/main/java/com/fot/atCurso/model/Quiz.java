package com.fot.atCurso.model;


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

import com.fot.atCurso.enums.ModalityEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Quiz {
	
	public static final String FIELD_ID_TAG = "idTag";
	public static final String FIELD_ID_QUIZ = "idQuiz";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name= FIELD_ID_QUIZ)
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
      joinColumns=@JoinColumn(name= FIELD_ID_QUIZ, referencedColumnName= FIELD_ID_QUIZ),
      inverseJoinColumns=@JoinColumn(name=Question.FIELD_ID_QUESTION, referencedColumnName=Question.FIELD_ID_QUESTION))
	private List<Question> question;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
      name="quiz_tag",
      joinColumns=@JoinColumn(name= FIELD_ID_QUIZ, referencedColumnName= FIELD_ID_QUIZ, nullable=false),
      inverseJoinColumns=@JoinColumn(name= FIELD_ID_TAG, referencedColumnName= FIELD_ID_TAG, nullable=false))
	private List<Tag> tag;
}
