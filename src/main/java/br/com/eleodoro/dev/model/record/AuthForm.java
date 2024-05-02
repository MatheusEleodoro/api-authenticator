package br.com.eleodoro.dev.model.record;

import jakarta.validation.constraints.NotBlank;

public record AuthForm(@NotBlank(message = "Username é obrigatório") String username,
                       @NotBlank(message = "Password é obrigatório") String password,
                       @NotBlank(message = "Realm é obrigatório") String realm) {
}
