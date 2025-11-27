package com.clinica.clinica.Controller;

import com.clinica.clinica.model.Paciente;
import com.clinica.clinica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public String listarPacientes(Model model) {
        model.addAttribute("pacientes", pacienteService.findAll());
        return "pacientes/list-pacientes";
    }

    @GetMapping("/novo")
    public String novoPacienteForm(Model model) {
        model.addAttribute("paciente", new Paciente());
        return "pacientes/form-pacienete";
    }

    @PostMapping("/salvar")
    public String salvarPaciente(@ModelAttribute Paciente paciente) {
        pacienteService.save(paciente);
        return "redirect:/pacientes";
    }

    @GetMapping("/editar/{id}")
    public String editarPacienteForm(@PathVariable Integer id, Model model) {
        pacienteService.findById(id).ifPresent(paciente -> model.addAttribute("paciente", paciente));
        return "paciente/form-paciente";
    }

    @GetMapping("/excluir/{id}")
    public String excluirPaciente(@PathVariable Integer id) {
        pacienteService.deleteById(id);
        return "redirect:/pacientes";
    }
}
