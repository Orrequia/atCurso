package com.fot.atCurso.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Answer { 
	
	@Id
	@GeneratedValue
	private Integer idAnswer;
	
	@Column(nullable = false)
	private String name;
	
	private Boolean correct;
	
	/*@JoinColumn(name = FIELD_QUESTION)
	@ManyToOne(fetch = FetchType.LAZY)
	private Question question;*/
}
