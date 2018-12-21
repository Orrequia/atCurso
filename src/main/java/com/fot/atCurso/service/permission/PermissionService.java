package com.fot.atCurso.service.permission;

import com.fot.atCurso.model.Permission;
import com.fot.atCurso.service.AbstractService;

import java.util.List;

public interface PermissionService extends AbstractService<Permission, Integer> {

    List<Permission> getPermissionsByNameRole(String name);
}
