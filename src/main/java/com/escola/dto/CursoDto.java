package com.escola.dto;

import com.escola.entidade.Aluno;
import com.escola.entidade.Curso;
import com.escola.enums.Modalidade;
import com.escola.enums.NomeCurso;
import com.escola.enums.Turno;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public record CursoDto(
        Long id,
        @Enumerated(EnumType.STRING)
        NomeCurso nomeCurso,
        @Enumerated(EnumType.STRING)
        Modalidade modalidade,
        @Enumerated(EnumType.STRING)
        Turno turno,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate dataInicio,
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
        LocalDate dataFim,
        List<Aluno> aluno) {
    public CursoDto(Curso cadastre) {
     this(
             cadastre.getId(),
             cadastre.getNomeCurso(),
             cadastre.getModalidade(),
             cadastre.getTurno(),
             cadastre.getDataInicio(),
             cadastre.getDataFim(),
             cadastre.getAluno());
    }
}