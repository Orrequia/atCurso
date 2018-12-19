package com.fot.atCurso.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
class Permission {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idPermission;

    @Column
    private String name;
}