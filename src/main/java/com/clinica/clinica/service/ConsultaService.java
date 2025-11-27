package com.clinica.clinica.service;

import com.clinica.clinica.model.Consulta;
import com.clinica.clinica.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    public List<Consulta> findAll() {
        return consultaRepository.findAll();
    }

    public Optional<Consulta> findById(Integer id) {
        return consultaRepository.findById(id);
    }

    public Consulta save(Consulta consulta) {
        return consultaRepository.save(consulta);
    }

    public void deleteById(Integer id) {
        consultaRepository.deleteById(id);
    }
}
