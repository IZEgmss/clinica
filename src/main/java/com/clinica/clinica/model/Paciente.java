package com.clinica.clinica.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String cpf;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Consulta> consultas;

    @ManyToMany
    @JoinTable(
            name = "paciente_exame", // Nome da tabela intermediária no banco
            joinColumns = @JoinColumn(name = "paciente_id"), // Coluna que referencia o Paciente
            inverseJoinColumns = @JoinColumn(name = "exame_id") // Coluna que referencia o Exame
    )
    private List<Exame> exames;
    // ---------------------------
}
