package br.com.eleodoro.dev.model.dto;

import br.com.eleodoro.dev.model.UserEntity;
import br.com.eleodoro.dev.model.record.CredentialsForm;
import br.com.eleodoro.dev.utils.converter.ByteBoolean;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.persistence.Convert;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.List;


/**
 * DTO for {@link br.com.eleodoro.dev.model.UserEntity}
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Builder
public class UserDto implements Serializable {
    @Size(max = 36)
    private String id;
    @Size(max = 255)
    private String email;
    @NotNull
    @Convert(converter = ByteBoolean.class)
    private boolean emailVerified;
    @NotNull
    @Convert(converter = ByteBoolean.class)
    private  boolean enabled;
    @Size(max = 255)
    private String firstName;
    @Size(max = 255)
    private String lastName;
    @Size(max = 255)
    private  String username;
    private  Long createdTimestamp;

    private List<CredentialsForm> credentials;

    public UserDto(UserEntity entity) {
        BeanUtils.copyProperties(entity,this);
    }
}