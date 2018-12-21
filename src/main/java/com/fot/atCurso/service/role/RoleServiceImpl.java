package com.fot.atCurso.service.role;

import com.fot.atCurso.dao.RoleDAO;
import com.fot.atCurso.model.Role;
import com.fot.atCurso.service.AbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends AbstractServiceImpl<Role, RoleDAO> implements RoleService {

    private final
    RoleDAO roleDAO;

    @Autowired
    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public Role getRoleByUsernameUser(String username) {
        return roleDAO.findRoleByUsernameUser(username);
    }
}
