package com.clinica.clinica.Controller;

import com.clinica.clinica.model.Medico;
import com.clinica.clinica.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping
    public String listarMedicos(Model model) {
        model.addAttribute("medicos", medicoService.findAll());
        return "medicos/list-medicos";
    }

    @GetMapping("/novo")
    public String novoMedicoForm(Model model) {
        model.addAttribute("medico", new Medico());
        return "medicos/form-medico";
    }

    @PostMapping("/salvar")
    public String salvarMedico(@ModelAttribute Medico medico) {
        medicoService.save(medico);
        return "redirect:/medicos";
    }

    @GetMapping("/editar/{id}")
    public String editarMedicoForm(@PathVariable Integer id, Model model) {
        medicoService.findById(id).ifPresent(medico -> model.addAttribute("medico", medico));
        return "medicos/form-medico";
    }

    @GetMapping("/excluir/{id}")
    public String excluirMedico(@PathVariable Integer id) {
        medicoService.deleteById(id);
        return "redirect:/medicos";
    }
}
