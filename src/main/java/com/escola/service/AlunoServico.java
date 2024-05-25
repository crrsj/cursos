package com.escola.service;

import com.escola.dto.AlunoDto;
import com.escola.entidade.Aluno;
import com.escola.entidade.Curso;
import com.escola.enums.NomeCurso;
import com.escola.repositorio.AlunoRepositorio;
import com.escola.repositorio.CursoRepositorio;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunoServico {

    private final AlunoRepositorio alunoRepositorio;
    private final CursoRepositorio cursoRepositorio;

    @Transactional
    public Aluno cadastrarAluno(AlunoDto aluno){
        var cadastrarAluno = aluno.novoAluno(cursoRepositorio);

        return alunoRepositorio.save(cadastrarAluno);
    }
    public List<Aluno>ListarAlunos(){
        return alunoRepositorio.findAll();

    }
    public Aluno buscarAlunoPorId(Long id){
        return alunoRepositorio.findById(id).get();
    }
    public Aluno atualizarAluno(AlunoDto aluno,Long id){
        var atualize = new Aluno(aluno);
        atualize.setId(id);
        return alunoRepositorio.save(atualize);
    }
    public void excluirAluno(Long id){
        alunoRepositorio.findById(id);
        alunoRepositorio.deleteById(id);
    }


    /*public void associarCursoAoAluno(Curso curso){
        var aluno = new Aluno();
        if (curso.getNomeCurso()==NomeCurso.BACKEND){
            aluno.setCursoId(1l);
        }
    }*/
}
