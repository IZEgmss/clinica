package com.clinica.clinica.Controller;

import com.clinica.clinica.model.Consulta;
import com.clinica.clinica.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @Autowired
    private MedicoService medicoService;

    @GetMapping
    public String listarConsultas(Model model) {
        model.addAttribute("consultas", consultaService.findAll());
        return "consultas/list-consultas";
    }

    @GetMapping("/nova")
    public String novaConsultaForm(Model model) {
        model.addAttribute("consulta", new Consulta());
        model.addAttribute("medicos", medicoService.findAll());
        return "consultas/form-consulta";
    }

    @PostMapping("/salvar")
    public String salvarConsulta(@ModelAttribute Consulta consulta) {
        consultaService.save(consulta);
        return "redirect:/consultas";
    }

    @GetMapping("/editar/{id}")
    public String editarConsultaForm(@PathVariable Integer id, Model model) {
        consultaService.findById(id).ifPresent(consulta -> model.addAttribute("consulta", consulta));
        model.addAttribute("medicos", medicoService.findAll());
        return "consultas/form-consulta";
    }

    @GetMapping("/excluir/{id}")
    public String excluirConsulta(@PathVariable Integer id) {
        consultaService.deleteById(id);
        return "redirect:/consultas";
    }
}
