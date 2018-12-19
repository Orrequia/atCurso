package com.fot.atCurso.controller;

import com.fot.atCurso.model.CustomPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Slf4j
public class ResourceController {

    @GetMapping("/context")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String context() {

        log.info(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        String principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();

        return principal;
    }

    @GetMapping("/secured")
    @PreAuthorize("hasAuthority('role_admin')")
    public String secured(CustomPrincipal principal) {
        return principal.getUsername() + " " + principal.getEmail();
    }

    @GetMapping("/resource")
    public String resource() {
        return UUID.randomUUID().toString();
    }
}
