package com.escola.entidade;

import com.escola.dto.AlunoDto;
import com.escola.dto.CursoDto;
import com.escola.enums.NomeCurso;
import com.escola.enums.Turno;
import com.escola.enums.Modalidade;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cursos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Curso implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private NomeCurso nomeCurso;
    private Modalidade modalidade;
    private Turno turno;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    @OneToMany(mappedBy = "curso")
    @JsonIgnore
    private List<Aluno>aluno = new ArrayList<>();

    public Curso(CursoDto curso) {
        this.nomeCurso = curso.nomeCurso();
        this.modalidade = curso.modalidade();
        this.turno = curso.turno();
        this.dataInicio = curso.dataInicio();
        this.dataFim = curso.dataFim();
        this.aluno = curso.aluno();
    }


}
