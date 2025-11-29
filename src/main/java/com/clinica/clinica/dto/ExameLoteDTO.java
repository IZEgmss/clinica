package com.clinica.clinica.dto;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class ExameLoteDTO {
    private Integer pacienteId;
    private List<ExameItem> exames = new ArrayList<>();

    @Data
    public static class ExameItem {
        private String nome;
        private String descricao;
    }
}
