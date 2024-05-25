package com.escola.Controle;

import com.escola.dto.AlunoDto;
import com.escola.service.AlunoServico;
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
@RequestMapping("aluno")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AlunoControle {

    private final AlunoServico alunoServico;
    @PostMapping
    @Operation(summary = "Rota responsável por cadastrar um aluno")
    @ApiResponse(responseCode = "201",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<AlunoDto>cadastrarAluno(@RequestBody @Valid AlunoDto aluno){
        var cadastrarAluno = alunoServico.cadastrarAluno(aluno);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("aluno/{id}")
                .buildAndExpand(cadastrarAluno.getId()).toUri();
        return ResponseEntity.created(uri).body(new AlunoDto(cadastrarAluno));
    }
    @GetMapping @Operation(summary = "Rota responsável pela busca de todos os alunos")
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<List<AlunoDto>>listarAlunos(){
        var listar = alunoServico.ListarAlunos().stream().map(AlunoDto::new).toList();
        return ResponseEntity.ok(listar);
    }

    @GetMapping("{id}")
    @Operation(summary = "Rota responsável por buscar um aluno pelo ID")
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<AlunoDto>buscarPorid(@PathVariable Long id){
        var buscaId = alunoServico.buscarAlunoPorId(id);
        return ResponseEntity.ok().body(new AlunoDto(buscaId));
    }

    @PutMapping("{id}")
    @Operation(summary = "Rota responsável por atualizar aluno pelo ID")
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<AlunoDto>atualizarAluno(@RequestBody AlunoDto aluno,@PathVariable Long id){
        var atualize = alunoServico.atualizarAluno(aluno,id);
        return ResponseEntity.ok().body(new AlunoDto(atualize));
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Rota responsável por deletar um aluno pelo ID")
    @ApiResponse(responseCode = "204",description = " sem conteúdo",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<Void>excluir(@PathVariable Long id){
        alunoServico.excluirAluno(id);
        return ResponseEntity.noContent().build();
    }

}
