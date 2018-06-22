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
public class Tag {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idTag;
	
	@Column(nullable = false, unique = true)
	private String name;
}
