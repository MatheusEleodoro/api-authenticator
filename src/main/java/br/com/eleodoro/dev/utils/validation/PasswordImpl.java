package br.com.eleodoro.dev.utils.validation;

import br.com.eleodoro.dev.utils.validation.exception.InvalidPasswordException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordImpl implements ConstraintValidator<Password, String> {
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) {
            return false; // Tratar senhas nulas
        }
        Pattern pattern = Pattern.compile("^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\w\\s:])(\\S){8,16}$");
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            throw new InvalidPasswordException("A senha não atende aos requisitos mínimos",
                    List.of("A senha deve conter 1 número (0-9)",
                            "A senha deve conter 1 letra maiúscula",
                            "A senha deve conter 1 letra minúscula",
                            "A senha deve conter 1 número não alfanumérico",
                            "A senha é de 8 a 16 caracteres sem espaço"));
        }
        return true;
    }
}
