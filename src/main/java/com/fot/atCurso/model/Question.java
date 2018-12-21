package com.fot.atCurso.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Question {

	public static final String FIELD_ID_TAG = "idTag";
	public static final String FIELD_ID_DIFFICULTY = "idDifficulty";
	public static final String FIELD_ID_QUESTION = "idQuestion";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name= FIELD_ID_QUESTION)
	private Integer idQuestion;
	
	@Column(nullable = false)
	private String name;
	
	@JoinColumn(name = FIELD_ID_TAG, nullable=false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Tag tag;
	
	@JoinColumn(name = FIELD_ID_DIFFICULTY, nullable=false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Difficulty difficulty;
	
	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.REMOVE)
	@JoinColumn(name= FIELD_ID_QUESTION, referencedColumnName= FIELD_ID_QUESTION)
	private List<Answer> answer;
}
