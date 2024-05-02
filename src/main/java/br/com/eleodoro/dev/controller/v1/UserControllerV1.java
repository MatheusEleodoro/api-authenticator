package br.com.eleodoro.dev.controller.v1;

import br.com.eleodoro.dev.model.dto.UserDto;
import br.com.eleodoro.dev.model.record.CreateForm;
import br.com.eleodoro.dev.model.record.ResetForm;
import br.com.eleodoro.dev.service.UserService;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserControllerV1 {
    @NonNull
    private UserService service;



    @GetMapping("search/{realm}")
    public ResponseEntity<Object> findUsers(@PathVariable String realm, @RequestParam(name = "q") String queryString, Authentication auth) {
        return ResponseEntity.ok(service.findUsers(realm,queryString,auth));
    }

    @PostMapping("create")
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid CreateForm form, Authentication auth) {
        return service.createUser(form,auth);
    }

    @PutMapping("{userId}/reset-password")
    public ResponseEntity<Void> resetPassword(@PathVariable String userId, @RequestBody @Valid ResetForm form, Authentication auth) {
        return service.resetPassword(userId,form,auth);
    }
}
