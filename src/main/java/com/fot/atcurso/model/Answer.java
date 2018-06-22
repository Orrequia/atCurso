package com.fot.atcurso.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idAnswer;
	
	@Column(nullable = false)
	private String name;
	
	private Boolean correct;
}
