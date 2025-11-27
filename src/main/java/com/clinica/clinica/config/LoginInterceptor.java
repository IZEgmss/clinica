package com.clinica.clinica.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.clinica.clinica.model.Usuario;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        
        // Permitir acesso a recursos públicos
        if (uri.contains("/usuarios/login") || uri.contains("/usuarios/cadastro") || 
            uri.contains("/css/") || uri.contains("/js/") || uri.contains("/images/")) {
            return true;
        }
        
        // Verificar se o usuário está logado
        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");
        if (usuarioLogado == null) {
            response.sendRedirect("/usuarios/login");
            return false;
        }
        
        // Verificar permissões baseadas no tipo de usuário
        if (uri.contains("/admin/") && !"ADMIN".equals(usuarioLogado.getTipoUsuario())) {
            response.sendRedirect("/usuarios/login");
            return false;
        }
        
        if (uri.contains("/medicos/") && !("MEDICO".equals(usuarioLogado.getTipoUsuario()) || "ADMIN".equals(usuarioLogado.getTipoUsuario()))) {
            response.sendRedirect("/usuarios/login");
            return false;
        }
        
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // Adicionar informações do usuário logado ao modelo, se existir
        if (modelAndView != null) {
            Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");
            if (usuarioLogado != null) {
                modelAndView.addObject("usuarioLogado", usuarioLogado);
            }
        }
    }
}