package br.com.eleodoro.dev.controller.v1;

import br.com.eleodoro.dev.model.record.AuthForm;
import br.com.eleodoro.dev.service.Authenticator;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticatorControllerV1 {
    @NonNull
    private Authenticator authenticator;

    @GetMapping
    public Object authenticate(@RequestBody @Valid AuthForm user){
        return authenticator.authenticate(user);
    }
}
