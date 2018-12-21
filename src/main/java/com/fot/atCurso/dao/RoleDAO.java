package com.fot.atCurso.dao;

import com.fot.atCurso.model.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDAO extends GenericDAO<Role>{

    @Query(value = "SELECT r FROM User AS u JOIN u.role AS r WHERE u.username = :username")
    Role findRoleByUsernameUser(@Param("username") String username);
}
