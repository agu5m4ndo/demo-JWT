package com.example.springsecurityjwt.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    String usuario;
    String contrasenia;
    String nombre;
    String apellido;
    String pais;
}
