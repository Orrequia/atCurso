package com.fot.atCurso.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Role {

    public static final String FIELD_ID_PERMISSION = "idPermission";
    public static final String FIELD_ID_ROLE = "idRole";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idRole;

    @Column
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="role_permission",
            joinColumns=@JoinColumn(name= FIELD_ID_ROLE, referencedColumnName= FIELD_ID_ROLE),
            inverseJoinColumns=@JoinColumn(name= FIELD_ID_PERMISSION, referencedColumnName= FIELD_ID_PERMISSION))
    private List<Permission> permission;
}