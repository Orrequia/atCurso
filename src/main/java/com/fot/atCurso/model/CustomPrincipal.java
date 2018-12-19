package com.fot.atCurso.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class CustomPrincipal implements Serializable {

    private String username;
    private String email;
}
