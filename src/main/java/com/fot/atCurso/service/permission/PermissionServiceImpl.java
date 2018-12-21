package com.fot.atCurso.service.permission;

import com.fot.atCurso.dao.PermissionDAO;
import com.fot.atCurso.model.Permission;
import com.fot.atCurso.service.AbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl extends AbstractServiceImpl<Permission, PermissionDAO> implements PermissionService {

    private final
    PermissionDAO permissionDAO;

    @Autowired
    public PermissionServiceImpl(PermissionDAO permissionDAO) {
        this.permissionDAO = permissionDAO;
    }

    @Override
    public List<Permission> getPermissionsByNameRole(String name) {
        return permissionDAO.findPermissionByNameRole(name);
    }
}
