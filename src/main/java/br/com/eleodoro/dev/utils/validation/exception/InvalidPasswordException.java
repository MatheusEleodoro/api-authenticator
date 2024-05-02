package br.com.eleodoro.dev.utils.validation.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class InvalidPasswordException extends RuntimeException {
    private final List<String> messages;

    public InvalidPasswordException(String message, List<String> messages) {
        super(message);
        this.messages = messages;
    }
}
