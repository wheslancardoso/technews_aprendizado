package br.com.newsletter.tech_newsletter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice // 1. Anotação que ativa o gerenciamento global de exceções
public class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class) // 2. Diz que este método trata exceções de validação
    public ResponseEntity<List<String>> handleValidationExceptions(MethodArgumentNotValidException ex) {

        // 3. Pega todos os erros de validação, extrai as mensagens padrão e coloca em uma lista
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        // 4. Retorna uma resposta com status 400 (BAD_REQUEST) e a lista de erros no corpo
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}