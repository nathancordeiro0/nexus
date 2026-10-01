package br.com.nexus.commons.dto.response.user;

import br.com.nexus.commons.enums.UserRoles;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserResponseV1 {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private UserRoles role;
}
