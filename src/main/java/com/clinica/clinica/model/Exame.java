package com.clinica.clinica.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Exame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome; // Ex: Hemograma, Raio-X
    private String descricao;

    // O "mappedBy" indica que o lado forte da relação (quem manda na tabela de junção) é o Paciente
    @ManyToMany(mappedBy = "exames")
    private List<Paciente> pacientes;
}