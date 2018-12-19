package com.fot.atCurso.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
class Role {

    public static final String FIELD_IDPERMISSION = "idPermission";
    public static final String FIELD_IDROLE = "idRole";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idRole;

    @Column
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="role_permission",
            joinColumns=@JoinColumn(name=FIELD_IDROLE, referencedColumnName=FIELD_IDROLE),
            inverseJoinColumns=@JoinColumn(name=FIELD_IDPERMISSION, referencedColumnName=FIELD_IDPERMISSION))
    private List<Permission> permission;
}