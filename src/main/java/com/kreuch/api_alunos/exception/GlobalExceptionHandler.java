package com.kreuch.api_alunos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.List;

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(AlunoNaoEncontradoException.class)
    ResponseEntity<ErrorResponse> handleAlunNaoEncontradoException(AlunoNaoEncontradoException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(404, ex.getMessage(), Instant.now()));
    }

    @ExceptionHandler(EmailJaCadastradoException.class)
    ResponseEntity<ErrorResponse> handleEmailJaCadastradoException(EmailJaCadastradoException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(409,ex.getMessage(),Instant.now()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ErrorAtributeResponse> handleValidacaoRequest(MethodArgumentNotValidException ex){
        List<ErrorAtribute> erros = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(erro ->
                        new ErrorAtribute(erro.getField(),
                                erro.getDefaultMessage()))
                .toList();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorAtributeResponse(400,"Erro de validação", Instant.now(), erros));
    }
}
