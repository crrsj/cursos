package com.escola.entidade;

import com.escola.dto.AlunoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

@Entity
@Table(name ="alunos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aluno implements Serializable {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(unique = true)
    private String cpf;
    private String fone;
    private Date dataNasc;
    private Long cursoId;
    @JoinColumn(name ="Id_curso")
    @ManyToOne
    private Curso curso;

    public Aluno(AlunoDto aluno) {
        this.id = aluno.id();
        this.nome = aluno.nome();
        this.cpf = aluno.cpf();
        this.dataNasc = aluno.dataNasc();
        this.cursoId = aluno.cursoId();
        this.curso = aluno.curso();
    }
}
