package com.clinica.clinica.Controller;

import com.clinica.clinica.model.Exame;
import com.clinica.clinica.dto.ExameLoteDTO;
import com.clinica.clinica.service.ExameService;
import com.clinica.clinica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/exames")
public class ExameController {

    @Autowired
    private ExameService exameService;

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public String listarExames(Model model) {
        List<Exame> allExams = exameService.findAll();
        Map<String, List<Exame>> examesPorPaciente = allExams.stream()
                .collect(Collectors.groupingBy(
                        e -> e.getPaciente() != null ? e.getPaciente().getNome() : "Sem Paciente",
                        TreeMap::new,
                        Collectors.toList()
                ));
        model.addAttribute("examesPorPaciente", examesPorPaciente);
        return "exames/list-exames";
    }

    @GetMapping("/novo")
    public String novoExameForm(Model model) {
        model.addAttribute("exame", new Exame());
        model.addAttribute("pacientes", pacienteService.findAll());
        return "exames/form-exame";
    }

    @PostMapping("/salvar")
    public String salvarExame(@ModelAttribute Exame exame) {
        exameService.save(exame);
        return "redirect:/exames";
    }

    @GetMapping("/editar/{id}")
    public String editarExameForm(@PathVariable Integer id, Model model) {
        exameService.findById(id).ifPresent(exame -> model.addAttribute("exame", exame));
        model.addAttribute("pacientes", pacienteService.findAll());
        return "exames/form-exame";
    }

    @GetMapping("/excluir/{id}")
    public String excluirExame(@PathVariable Integer id) {
        exameService.deleteById(id);
        return "redirect:/exames";
    }

    @GetMapping("/lote")
    public String novoExameLoteForm(Model model) {
        ExameLoteDTO dto = new ExameLoteDTO();
        dto.getExames().add(new ExameLoteDTO.ExameItem()); // Add one initial row
        model.addAttribute("exameLote", dto);
        model.addAttribute("pacientes", pacienteService.findAll());
        return "exames/form-exame-lote";
    }

    @PostMapping("/salvar-lote")
    public String salvarExameLote(@ModelAttribute ExameLoteDTO exameLote) {
        pacienteService.findById(exameLote.getPacienteId()).ifPresent(paciente -> {
            for (ExameLoteDTO.ExameItem item : exameLote.getExames()) {
                if (item.getNome() != null && !item.getNome().isEmpty()) {
                    Exame exame = new Exame();
                    exame.setNome(item.getNome());
                    exame.setDescricao(item.getDescricao());
                    exame.setPaciente(paciente);
                    exameService.save(exame);
                }
            }
        });
        return "redirect:/exames";
    }
}

