package com.fot.atCurso.model;


import java.util.List;

import javax.persistence.Basic;
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
import javax.persistence.ManyToOne;

import com.fot.atCurso.enums.ModalityEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Questionary {
	
	public static final String FIELD_IDTAG = "idTag";
	public static final String FIELD_IDQUESTIONARY = "idQuestionary";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name=FIELD_IDQUESTIONARY)
	private Integer idQuestionary;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private Integer nQuestion;
	
	@Enumerated(EnumType.STRING)
	private ModalityEnum modality;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
      name="questionary_question",
      joinColumns=@JoinColumn(name=FIELD_IDQUESTIONARY, referencedColumnName=FIELD_IDQUESTIONARY),
      inverseJoinColumns=@JoinColumn(name=Question.FIELD_IDQUESTION, referencedColumnName=Question.FIELD_IDQUESTION))
	private List<Question> question;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
      name="questionary_tag",
      joinColumns=@JoinColumn(name=FIELD_IDQUESTIONARY, referencedColumnName=FIELD_IDQUESTIONARY),
      inverseJoinColumns=@JoinColumn(name=FIELD_IDTAG, referencedColumnName=FIELD_IDTAG))
	private List<Tag> tag;
}
