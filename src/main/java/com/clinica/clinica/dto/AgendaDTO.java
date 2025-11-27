package com.clinica.clinica.dto;

import com.clinica.clinica.model.Consulta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgendaDTO {
    private String nomeMedico;
    private String especialidadeMedico;
    private List<Consulta> consultas;
}
