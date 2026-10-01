package br.com.nexus.entities;

import br.com.nexus.commons.enums.UserRoles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private UserRoles role;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
