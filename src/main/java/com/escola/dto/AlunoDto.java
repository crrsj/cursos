package com.escola.dto;

import com.escola.entidade.Aluno;
import com.escola.entidade.Curso;
import com.escola.enums.NomeCurso;
import com.escola.repositorio.CursoRepositorio;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;



public record AlunoDto(
        Long id,
        @NotBlank(message = "não pode estar em branco")
        String nome,

        @NotBlank(message = "não pode estar em branco")
        String cpf,
        @NotBlank(message = "não pode estar em branco")
        String fone,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        Date dataNasc,
        @NotNull(message = "não pode ser nulo")
        Long cursoId,
        Curso curso) {


    public AlunoDto(Aluno cadastrarAluno) {
        this(
                cadastrarAluno.getId(),
                cadastrarAluno.getNome(),
                cadastrarAluno.getCpf(),
                cadastrarAluno.getFone(),
                cadastrarAluno.getDataNasc(),
                cadastrarAluno.getCursoId(),
                cadastrarAluno.getCurso());
    }

    public Aluno novoAluno(CursoRepositorio cursoRepositorio) {

        var curso = cursoRepositorio.findById(cursoId).get();

        return new Aluno(id,nome,cpf,fone,dataNasc,cursoId,curso);
    }

}
