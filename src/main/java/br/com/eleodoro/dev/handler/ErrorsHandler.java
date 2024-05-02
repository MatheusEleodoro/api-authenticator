package br.com.eleodoro.dev.handler;

import br.com.eleodoro.dev.utils.validation.exception.InvalidPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Map;
import java.util.stream.Collectors;


@RestControllerAdvice
public class ErrorsHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handler(MethodArgumentNotValidException e) {
        return e.getBindingResult().getAllErrors()
                .stream().collect(Collectors.toMap(
                        error -> ((FieldError) error).getField(),
                        error -> error.getDefaultMessage() == null ? "inválido" : error.getDefaultMessage()
                ));
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<Map<String, Object>>handler(HttpClientErrorException e) {
        var message = switch (e.getStatusCode()){
            case HttpStatus.UNAUTHORIZED -> "Credenciais inválidas";
            case HttpStatus.CONFLICT -> "E-mail já cadastrado";
            default -> e.getMessage();
        };
        return ResponseEntity.status(e.getStatusCode())
                .body(Map.of("message",message));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidPasswordException.class)
    public Map<String, Object> handler(InvalidPasswordException e) {
        return Map.of("message",e.getMessage(),"warning",e.getMessages());
    }
}
