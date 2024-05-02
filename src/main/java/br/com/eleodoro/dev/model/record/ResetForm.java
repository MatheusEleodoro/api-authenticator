package br.com.eleodoro.dev.model.record;

import br.com.eleodoro.dev.utils.validation.Password;
import jakarta.validation.constraints.NotBlank;

public record ResetForm(@Password
                        @NotBlank(message = "Password é obrigatório") String password,
                        @NotBlank(message = "Realm é obrigatório") String realm) {
}
