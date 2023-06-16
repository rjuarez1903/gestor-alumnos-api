package com.alkemy.gestoralumnos.dto;

import com.alkemy.gestoralumnos.models.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    private String name;
    private String surname;
    private String dni;
    private String email;
    private String password;
    private Role role;
}
