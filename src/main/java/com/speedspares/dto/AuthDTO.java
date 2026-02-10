package com.speedspares.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class AuthDTO {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginRequest {
        @NotBlank(message = "El email es obligatorio")
        @Email(message = "El email debe ser válido")
        private String email;

        @NotBlank(message = "La contraseña es obligatoria")
        private String password;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegisterRequest {
        @NotBlank(message = "El nombre completo es obligatorio")
        private String nombreCompleto;

        @NotBlank(message = "El email es obligatorio")
        @Email(message = "El email debe ser válido")
        private String email;

        @NotBlank(message = "La contraseña es obligatoria")
        @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
        private String password;

        private String telefono;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private String token;
        private UsuarioDTO usuario;
    }
}
