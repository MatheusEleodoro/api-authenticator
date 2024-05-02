package br.com.eleodoro.dev.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class UserRoleMappingId implements Serializable {
    private static final long serialVersionUID = 2992530499456356532L;
    @Size(max = 255)
    @NotNull
    @Column(name = "ROLE_ID", nullable = false)
    private String roleId;

    @Size(max = 36)
    @NotNull
    @Column(name = "USER_ID", nullable = false, length = 36)
    private String userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserRoleMappingId entity = (UserRoleMappingId) o;
        return Objects.equals(this.roleId, entity.roleId) &&
                Objects.equals(this.userId, entity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, userId);
    }

}