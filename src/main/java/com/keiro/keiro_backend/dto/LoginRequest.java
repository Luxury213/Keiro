package com.keiro.keiro_backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequest {

    @NotBlank(message = "El email es requerido")
    @Email(message = "Email inválido")
    private String email;

    @NotBlank(message = "La contraseña es requerida")
    private String password;
}
