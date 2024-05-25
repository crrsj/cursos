package com.escola.infra;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class TratarExcessao {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<MensagemDeErros>tratarIdNaoEncontrado(){
      return ResponseEntity.ok().body(new MensagemDeErros(HttpStatus.NOT_FOUND,"ID não encontrado"));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<MensagemDeErros>tratarCpfDuplicado(){
        return ResponseEntity.ok().body(new MensagemDeErros(HttpStatus.BAD_REQUEST,"CPF duplicado"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?>tratador400(MethodArgumentNotValidException ex){
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(TratandoErros::new).toList());
    }
    public record TratandoErros(String campo,String mensagem) {
        public TratandoErros(FieldError erro) {
            this(erro.getField(),erro.getDefaultMessage());
        }

    }

}
