package com.example.springsecurityjwt.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RepositorioUsuario extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByUsername(String username);
}
