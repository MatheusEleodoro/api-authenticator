package br.com.eleodoro.dev.service.impl;

import br.com.eleodoro.dev.model.dto.UserDto;
import br.com.eleodoro.dev.model.record.CreateForm;
import br.com.eleodoro.dev.model.record.CredentialsForm;
import br.com.eleodoro.dev.model.record.ResetForm;
import br.com.eleodoro.dev.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private static final String AUTH = "Authorization";
    private static final String URL = "KEYCLOAK_URL";

    @NonNull
    private Environment env;


    @Override
    public Object findUsers(String realm, String queryString, Authentication auth) {
        String url = String.format("%s/admin/realms/%s/users", env.getProperty(URL), realm);
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(((Jwt) auth.getPrincipal()).getTokenValue());
        URI uri = URI.create(UriComponentsBuilder.fromUriString(url)
                .queryParam("q",queryString)
                .build()
                .toUriString());
        new RestTemplateBuilder().defaultHeader(AUTH, "Bearer " + headers.getFirst(AUTH))
                .build();
        return new RestTemplateBuilder().defaultHeader(AUTH, headers.getFirst(AUTH))
                .build().getForEntity(uri, Object.class).getBody();
    }

    @Override
    public ResponseEntity<UserDto> createUser(CreateForm createForm, Authentication auth) {
        String url = String.format("%s/admin/realms/%s/users", env.getProperty(URL), createForm.realm());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(((Jwt) auth.getPrincipal()).getTokenValue());
        HttpEntity<UserDto> entity = new HttpEntity<>(UserDto.builder()
                .email(createForm.email())
                .enabled(true)
                .username(createForm.username())
                .firstName(createForm.firstName())
                .lastName(createForm.lastName())
                .credentials(List.of(new CredentialsForm(false, "password", createForm.password())))
                .build(), headers);

        return new RestTemplate().postForEntity(URI.create(url), entity, UserDto.class);
    }

    @Override
    public ResponseEntity<Void> resetPassword(String userId, ResetForm form, Authentication auth) {
        String url = String.format("%s/admin/realms/%s/users/{userId}/reset-password", env.getProperty(URL), form.realm());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(((Jwt) auth.getPrincipal()).getTokenValue());
        HttpEntity<CredentialsForm> entity = new HttpEntity<>(new CredentialsForm(
                false,
                "password",
                form.password()),
                headers);

        try {
            new RestTemplate().put(url, entity,userId);
            return ResponseEntity.ok().build();
        } catch (RestClientException e) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }
}
