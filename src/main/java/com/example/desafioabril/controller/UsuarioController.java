package com.example.desafioabril.controller;

import com.example.desafioabril.dto.UsuarioDadosDTO;
import com.example.desafioabril.dto.UsuarioExibirDTO;
import com.example.desafioabril.model.Usuario;
import com.example.desafioabril.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastro")
    public ResponseEntity<String> cadastrarUsuario(@RequestBody UsuarioDadosDTO usuarioDto) {
        usuarioService.cadastrarUsuario(usuarioDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario cadastrado com sucesso!");
    }

    @GetMapping("/listar")
    public ResponseEntity<Map<String, Object>> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.listarUsuarios();

        return ResponseEntity.ok(Map.of(
                "message", "Lista de usuários.",
                "usuarios", usuarios
        ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioExibirDTO> editarUsuario(@PathVariable Long id, @RequestBody UsuarioDadosDTO usuarioDto) {
        UsuarioExibirDTO usuarioEdicao = usuarioService.editarUsuario(id, usuarioDto);

        return ResponseEntity.ok().body(usuarioEdicao);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletarUsuario(@PathVariable Long id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.ok("Usuario deletado com sucesso!");
    }

}
