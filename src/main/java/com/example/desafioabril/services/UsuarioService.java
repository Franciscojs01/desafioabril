package com.example.desafioabril.services;

import com.example.desafioabril.dto.UsuarioDadosDTO;
import com.example.desafioabril.dto.UsuarioExibirDTO;
import com.example.desafioabril.exceptions.UsuarioDuplicadoException;
import com.example.desafioabril.exceptions.UsuarioNotFoundException;
import com.example.desafioabril.model.Usuario;
import com.example.desafioabril.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioExibirDTO cadastrarUsuario(UsuarioDadosDTO usuariodTO) {
        String email = usuariodTO.getEmail();

        usuarioRepository.findByEmail(email)
                .ifPresent(usuarioExistente -> {
                    throw new UsuarioDuplicadoException("Usuário com esse email: " + email + " já existe");
                });

        String senhaCriptografada = passwordEncoder.encode(usuariodTO.getSenha());
        Usuario novoUsuario = new Usuario(usuariodTO.getNome(), email, senhaCriptografada);
        usuarioRepository.save(novoUsuario);
        return new UsuarioExibirDTO(novoUsuario.getNome(), novoUsuario.getEmail());

    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public UsuarioExibirDTO editarUsuario(long id, UsuarioDadosDTO usuariodTO) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuário com ID " + id + " não encontrado"));

        usuarioExistente.setNome(usuariodTO.getNome());
        usuarioExistente.setEmail(usuariodTO.getEmail());

        if (usuariodTO.getSenha() != null && !usuariodTO.getSenha().isBlank()) {
            String senhaCriptografada = passwordEncoder.encode(usuariodTO.getSenha());
            usuarioExistente.setSenha(senhaCriptografada);
        }

        usuarioRepository.save(usuarioExistente);

        return new UsuarioExibirDTO(usuarioExistente.getNome(), usuarioExistente.getEmail());
    }

    public void deletarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new UsuarioNotFoundException("Usuário não foi encontrado");
        }

        usuarioRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(username).orElseThrow(() -> new UsuarioNotFoundException("Usuário não encontrado!"));
    }
}
