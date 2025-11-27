package com.clinica.clinica.Controller;

import com.clinica.clinica.service.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/agenda", "/"}) // Responde na raiz e em /agenda
public class AgendaController {

    @Autowired
    private AgendaService agendaService;

    @GetMapping
    public String verAgenda(Model model) {
        model.addAttribute("agendas", agendaService.getAgendaCompleta());
        return "agenda/agenda-completa";
    }
}