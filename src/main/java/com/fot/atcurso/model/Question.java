package com.fot.atcurso.model;

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

	public static final String FIELD_IDTAG = "idTag";
	public static final String FIELD_IDDIFFICULTY = "idDifficulty";
	public static final String FIELD_IDQUESTION = "idQuestion";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name=FIELD_IDQUESTION)
	private Integer idQuestion;
	
	@Column(nullable = false)
	private String name;
	
	@JoinColumn(name = FIELD_IDTAG, nullable=false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Tag tag;
	
	@JoinColumn(name = FIELD_IDDIFFICULTY, nullable=false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Difficulty difficulty;
	
	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.REMOVE)
	@JoinColumn(name=FIELD_IDQUESTION, referencedColumnName=FIELD_IDQUESTION)
	private List<Answer> answer;
}
