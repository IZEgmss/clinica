package com.clinica.clinica.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinica.clinica.model.Usuario;
import com.clinica.clinica.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    public Usuario cadastrarUsuario(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }
        
        // Em um sistema real, você deve criptografar a senha antes de salvar
        // Exemplo: usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        
        return usuarioRepository.save(usuario);
    }
    
    public Optional<Usuario> autenticar(String email, String senha) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);
        
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            
            // Em um sistema real, você deve verificar a senha criptografada
            // Exemplo: if (passwordEncoder.matches(senha, usuario.getSenha()))
            if (senha.equals(usuario.getSenha())) {
                return Optional.of(usuario);
            }
        }
        
        return Optional.empty();
    }
    
    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }
    
    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
}