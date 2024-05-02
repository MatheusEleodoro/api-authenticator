package br.com.eleodoro.dev.service;

import br.com.eleodoro.dev.model.record.AuthForm;

public interface Authenticator {

    Object authenticate(AuthForm user);
}
