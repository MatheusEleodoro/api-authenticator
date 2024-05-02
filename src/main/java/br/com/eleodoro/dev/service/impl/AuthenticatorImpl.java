package br.com.eleodoro.dev.service.impl;

import br.com.eleodoro.dev.model.record.AuthForm;
import br.com.eleodoro.dev.service.Authenticator;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;


@Service
@RequiredArgsConstructor
public class AuthenticatorImpl implements Authenticator {
    @NonNull
    private Environment env;

    private static final String URL = "%s/realms/%s/protocol/openid-connect/token";

    @Override
    public Object authenticate(AuthForm user) {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String,String> form = new LinkedMultiValueMap<>();
        form.add("client_id", env.getProperty("CLIENT_ID"));
        form.add("client_secret", env.getProperty("CLIENT_SECRET"));
        form.add("grant_type", "password");
        form.add("username", user.username());
        form.add("password", user.password());

        URI uri = URI.create(String.format(URL, env.getProperty("KEYCLOAK_URL"),user.realm()));

        HttpEntity<MultiValueMap<String,String>> entity = new HttpEntity<>(form, headers);
        return rest.postForEntity(uri,entity, Object.class);
    }
}
