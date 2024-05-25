package com.escola.service;

import com.escola.dto.CursoDto;
import com.escola.entidade.Curso;
import com.escola.repositorio.CursoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CursoServico {

    private final CursoRepositorio cursoRepositorio;
    public Curso cadastrarCurso(CursoDto curso){
        var cursos = new Curso(curso);
        return cursoRepositorio.save(cursos);
    }

    public List<Curso>listarCursos(){
        return cursoRepositorio.findAll();
    }
    public Curso buscarCursoPorId(Long id){
        return cursoRepositorio.findById(id).get();
    }
    public Curso AtualizarCurso(CursoDto curso,Long id){
        var atualize = new Curso(curso);
        atualize.setId(id);
        return cursoRepositorio.save(atualize);
    }
    public void excluir(Long id){
        cursoRepositorio.findById(id);
        cursoRepositorio.deleteById(id);
    }
}
