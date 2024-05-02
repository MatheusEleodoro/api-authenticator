package br.com.eleodoro.dev.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "USER_ENTITY")
public class UserEntity {
    @Id
    @Size(max = 36)
    @Column(name = "ID", nullable = false, length = 36)
    private String id;

    @Size(max = 255)
    @Column(name = "EMAIL")
    private String email;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "EMAIL_VERIFIED", nullable = false)
    private Byte emailVerified;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "ENABLED", nullable = false)
    private Byte enabled;

    @Size(max = 255)
    @Column(name = "FIRST_NAME")
    private String firstName;

    @Size(max = 255)
    @Column(name = "LAST_NAME")
    private String lastName;

    @Size(max = 255)
    @Column(name = "REALM_ID")
    private String realmId;

    @Size(max = 255)
    @Column(name = "USERNAME")
    private String username;

    @Column(name = "CREATED_TIMESTAMP")
    private Long createdTimestamp;

    @OneToMany(mappedBy = "user")
    private Set<UserRoleMapping> userRoleMappings = new LinkedHashSet<>();

}