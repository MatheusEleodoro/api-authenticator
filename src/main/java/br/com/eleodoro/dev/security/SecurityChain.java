package br.com.eleodoro.dev.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

public interface SecurityChain {
    SecurityFilterChain filterChain(HttpSecurity http) throws SecurityException;
}
