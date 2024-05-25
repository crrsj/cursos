package com.escola.Controle;

import com.escola.dto.CursoDto;
import com.escola.service.CursoServico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("curso")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CursoControle {

    private final CursoServico cursoServico;


    @PostMapping
    @Operation(summary = "Rota responsável por cadastrar um curso")
    @ApiResponse(responseCode = "201",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<CursoDto> cadastrarCurso(@RequestBody @Valid CursoDto curso){
        var cadastre = cursoServico.cadastrarCurso(curso);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("curso/{id}")
                .buildAndExpand(cadastre.getId()).toUri();
        return  ResponseEntity.created(uri).body(new CursoDto(cadastre));
    }

    @GetMapping
    @Operation(summary = "Rota responsável pela busca de todos os cursos")
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<List<CursoDto>>listarCursos(){
        var lista = cursoServico.listarCursos().stream().map(CursoDto::new).toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("{id}")
    @Operation(summary = "Rota responsável pela busca do curso pelo ID")
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<CursoDto>buscarCursoPorId(@PathVariable Long id){
        var buscaId = cursoServico.buscarCursoPorId(id);
        return ResponseEntity.ok().body(new CursoDto(buscaId));
    }
    @PutMapping("{id}")
    @Operation(summary = "Rota responsável por atualizar cursos pelo ID")
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<CursoDto>AtualizarCursos(@RequestBody @Valid CursoDto curso,@PathVariable Long id){
        var atualize = cursoServico.AtualizarCurso(curso,id);
        return ResponseEntity.ok().body(new CursoDto(atualize));

    }
    @DeleteMapping("{id}")
    @Operation(summary = "Rota responsável por deletar um curso pelo id")
    @ApiResponse(responseCode = "204",description = " sem conteúdo",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<Void>excluirCurso(@PathVariable Long id){
        cursoServico.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
