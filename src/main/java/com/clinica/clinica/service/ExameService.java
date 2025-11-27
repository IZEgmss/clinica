package com.clinica.clinica.service;

import com.clinica.clinica.model.Exame;
import com.clinica.clinica.repository.ExameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExameService {

    @Autowired
    private ExameRepository exameRepository;

    public List<Exame> findAll() {
        return exameRepository.findAll();
    }

    public Optional<Exame> findById(Integer id) {
        return exameRepository.findById(id);
    }

    public Exame save(Exame exame) {
        return exameRepository.save(exame);
    }

    public void deleteById(Integer id) {
        exameRepository.deleteById(id);
    }
}