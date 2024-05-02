package br.com.eleodoro.dev.model.record;

import br.com.eleodoro.dev.utils.validation.Password;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public record CreateForm(
        @NotBlank(message = "Nome é obrigatório") String firstName,
        @Nullable String lastName,
        @NotBlank(message = "Email é obrigatório") String email,
        @NotBlank(message = "Username é obrigatório") String username,
        @Password
        @NotBlank(message = "Password é obrigatório") String password,
        @NotBlank(message = "Realm é obrigatório") String realm) implements Serializable {
}
