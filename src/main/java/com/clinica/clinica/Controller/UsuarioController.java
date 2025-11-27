package com.clinica.clinica.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.clinica.clinica.model.Usuario;
import com.clinica.clinica.service.UsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping("/cadastro")
    public String telaCadastro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuario/cadastro";
    }
    
    @PostMapping("/cadastro")
    public String cadastrar(@ModelAttribute Usuario usuario, RedirectAttributes redirectAttributes) {
        try {
            usuarioService.cadastrarUsuario(usuario);
            redirectAttributes.addFlashAttribute("mensagem", "Cadastro realizado com sucesso!");
            return "redirect:/usuarios/login";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", e.getMessage());
            return "redirect:/usuarios/cadastro";
        }
    }
    
    @GetMapping("/login")
    public String telaLogin() {
        return "usuario/login";
    }
    
    @PostMapping("/login")
    public String login(String email, String senha, HttpSession session, RedirectAttributes redirectAttributes) {
        try {
            var usuarioOpt = usuarioService.autenticar(email, senha);
            
            if (usuarioOpt.isPresent()) {
                Usuario usuario = usuarioOpt.get();
                session.setAttribute("usuarioLogado", usuario);
                
                // Redireciona com base no tipo de usuário
                if ("ADMIN".equals(usuario.getTipoUsuario())) {
                    return "redirect:/admin/dashboard";
                } else if ("MEDICO".equals(usuario.getTipoUsuario())) {
                    return "redirect:/medicos/dashboard";
                } else {
                    return "redirect:/pacientes/dashboard";
                }
            } else {
                redirectAttributes.addFlashAttribute("mensagemErro", "Email ou senha inválidos");
                return "redirect:/usuarios/login";
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro ao realizar login: " + e.getMessage());
            return "redirect:/usuarios/login";
        }
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/usuarios/login";
    }
}