package br.com.eleodoro.dev.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Collection;
import java.util.Map;

public class JWTConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    @Override
    public AbstractAuthenticationToken convert(Jwt source) {
        Map<String, Collection<String>> claims = source.getClaim("realm_access");
        var grants = claims.get("roles").stream().map(role -> new SimpleGrantedAuthority("ROLE_".concat(role.toUpperCase())));
        return new JwtAuthenticationToken(source,grants.toList());
    }
}
