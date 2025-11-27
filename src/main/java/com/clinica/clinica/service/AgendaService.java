package com.clinica.clinica.service;

import com.clinica.clinica.dto.AgendaDTO;
import com.clinica.clinica.model.Medico;
import com.clinica.clinica.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgendaService {

    @Autowired
    private MedicoRepository medicoRepository;

    public List<AgendaDTO> getAgendaCompleta() {
        List<Medico> medicos = medicoRepository.findAll();
        return medicos.stream()
                .map(medico -> new AgendaDTO(
                        medico.getNome(),
                        medico.getEspecialidade(),
                        medico.getConsultas()))
                .collect(Collectors.toList());
    }
}
