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
public class Tag {
	
	@Id
	@GeneratedValue
	private Integer idTag;
	
	@Column(nullable = false, unique = true)
	private String name;
}
