package br.com.eleodoro.dev.service;

import br.com.eleodoro.dev.model.dto.UserDto;
import br.com.eleodoro.dev.model.record.CreateForm;
import br.com.eleodoro.dev.model.record.ResetForm;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface UserService {

    Object findUsers(String realm, String queryString, Authentication auth);
    ResponseEntity<UserDto> createUser(CreateForm createForm, Authentication auth);
    ResponseEntity<Void> resetPassword(String userId, ResetForm form, Authentication auth);
}
