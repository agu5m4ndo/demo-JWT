package com.example.springsecurityjwt.Auth;

import com.example.springsecurityjwt.Jwt.JwtService;
import com.example.springsecurityjwt.User.Rol;
import com.example.springsecurityjwt.User.Usuario;
import com.example.springsecurityjwt.User.RepositorioUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final RepositorioUsuario repositorioUsuario;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsuario(), request.getContrasenia()));
        UserDetails user = repositorioUsuario.findByUsername(request.getUsuario()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterRequest request) {

        Usuario usuario = Usuario.builder()
                .username(request.getUsuario())
                .password(passwordEncoder.encode(request.getContrasenia()))
                .firstname(request.getNombre())
                .lastname(request.getApellido())
                .country(request.getPais())
                .rol(Rol.USUARIO)
                .build();

        repositorioUsuario.save(usuario);
        return AuthResponse.builder()
                .token(jwtService.getToken(usuario))
                .build();
    }
}
