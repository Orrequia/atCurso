package com.fot.atCurso.dao;

import com.fot.atCurso.model.Permission;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PermissionDAO extends GenericDAO<Permission> {

    @Query(value = "SELECT p FROM Role AS r JOIN r.permission AS p WHERE r.name = :name")
    List<Permission> findPermissionByNameRole(@Param("name") String name);
}
