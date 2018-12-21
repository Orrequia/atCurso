package com.fot.atCurso.component.converter;

import com.fot.atCurso.model.Role;
import com.fot.atCurso.service.permission.PermissionService;
import com.fot.atCurso.service.role.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
public class CustomUserAuthenticationConverter implements UserAuthenticationConverter {

    private final RoleService roleService;
    private final PermissionService permissionService;

    @Autowired
    public CustomUserAuthenticationConverter(PermissionService permissionService, RoleService roleService) {
        this.permissionService = permissionService;
        this.roleService = roleService;
    }

    @Override
    public Map<String, ?> convertUserAuthentication(Authentication userAuthentication) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put(USERNAME, userAuthentication.getName());

        if (userAuthentication.getAuthorities() != null && !userAuthentication.getAuthorities().isEmpty())
            response.put(AUTHORITIES, AuthorityUtils.authorityListToSet(userAuthentication.getAuthorities()));

        return response;
    }

    @Override
    public Authentication extractAuthentication(Map<String, ?> map) {

        log.info(map.get(USERNAME).toString());

        if (map.containsKey(USERNAME))
            return new UsernamePasswordAuthenticationToken(map.get(USERNAME).toString(), "N/A", getAuthorities(map));
        return null;
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Map<String, ?> map) {

        String username = map.get(USERNAME).toString();
        Role role = roleService.getRoleByUsernameUser(username);

        Set<GrantedAuthority> authorities = new HashSet<>();

        authorities.add(new SimpleGrantedAuthority(role.getName()));
        permissionService.getPermissionsByNameRole(role.getName()).forEach(p ->
                authorities.add(new SimpleGrantedAuthority(p.getName()))
        );
        return authorities;
    }
}
