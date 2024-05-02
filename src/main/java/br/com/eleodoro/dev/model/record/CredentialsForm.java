package br.com.eleodoro.dev.model.record;

import lombok.Builder;

@Builder
public record CredentialsForm(boolean temporary, String type, String value) {
}
