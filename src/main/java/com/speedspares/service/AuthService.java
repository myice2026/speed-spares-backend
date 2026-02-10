package com.speedspares.service;

import com.speedspares.dto.AuthDTO;
import com.speedspares.mapper.UsuarioMapper;
import com.speedspares.model.entity.Usuario;
import com.speedspares.model.entity.Rol;
import com.speedspares.repository.UsuarioRepository;
import com.speedspares.repository.RolRepository;
import com.speedspares.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;

@Service
public class AuthService {

        @Autowired
        private AuthenticationManager authenticationManager;

        @Autowired
        private UsuarioRepository usuarioRepository;

        @Autowired
        private RolRepository rolRepository;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Autowired
        private JwtTokenProvider tokenProvider;

        @Autowired
        private UsuarioMapper usuarioMapper;

        public AuthDTO.Response login(AuthDTO.LoginRequest request) {
                Authentication authentication = authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

                SecurityContextHolder.getContext().setAuthentication(authentication);
                String jwt = tokenProvider.generateToken(authentication);

                Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

                return AuthDTO.Response.builder()
                                .token(jwt)
                                .usuario(usuarioMapper.toDTO(usuario))
                                .build();
        }

        @Transactional
        public AuthDTO.Response register(AuthDTO.RegisterRequest request) { // Renamed to register to match controller
                                                                            // expectation if any, or standard
                if (usuarioRepository.existsByEmail(request.getEmail())) {
                        throw new RuntimeException("El email ya está registrado");
                }

                Usuario usuario = usuarioMapper.toEntity(request);
                usuario.setPassword(passwordEncoder.encode(request.getPassword()));

                // Assign default role CLIENTE
                Rol userRole = rolRepository.findByNombre("CLIENTE")
                                .orElseGet(() -> rolRepository.save(Rol.builder().nombre("CLIENTE").build()));

                usuario.setRoles(new HashSet<>(Collections.singletonList(userRole)));

                Usuario savedUsuario = usuarioRepository.save(usuario);

                // Generate token
                String jwt = tokenProvider.generateToken(savedUsuario.getEmail());

                return AuthDTO.Response.builder()
                                .token(jwt)
                                .usuario(usuarioMapper.toDTO(savedUsuario))
                                .build();
        }
}
