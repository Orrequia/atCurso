package com.fot.atCurso.service.role;

import com.fot.atCurso.model.Role;
import com.fot.atCurso.service.AbstractService;

public interface RoleService extends AbstractService<Role, Integer> {


    Role getRoleByUsernameUser(String username);
}
