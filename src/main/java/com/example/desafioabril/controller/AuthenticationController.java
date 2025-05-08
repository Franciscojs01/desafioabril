package com.example.desafioabril.controller;

import com.example.desafioabril.dto.AuthenticationDTO;
import com.example.desafioabril.dto.LoginDTO;
import com.example.desafioabril.infra.config.TokenService;
import com.example.desafioabril.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;


    @PostMapping("/login")
    public ResponseEntity<LoginDTO> login(@RequestBody AuthenticationDTO data) {
        Authentication usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        Authentication auth = authenticationManager.authenticate(usernamePassword);
        SecurityContextHolder.getContext().setAuthentication(auth);
        Usuario usuario = (Usuario) auth.getPrincipal();

        var token = tokenService.generateToken(usuario);

        return ResponseEntity.ok(new LoginDTO(usuario.getId_usuario(), usuario.getEmail(), token));
    }


}
