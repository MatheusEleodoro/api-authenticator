package br.com.eleodoro.dev.utils.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;


import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = { PasswordImpl.class })
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
public @interface Password {
    String message() default "A senha não atende aos requisitos mínimos";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}


