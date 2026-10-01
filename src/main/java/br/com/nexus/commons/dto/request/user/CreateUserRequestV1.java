package br.com.nexus.commons.dto.request.user;

import br.com.nexus.commons.enums.UserRoles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequestV1 {
    @NotBlank(message = "This field cannot be blank")
    private String firstName;

    @NotBlank(message = "This field cannot be blank")
    private String lastName;

    @NotBlank(message = "This field cannot be blank")
    @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,10}$", message = "The e-mail is not valid")
    private String email;

    @NotBlank(message = "This field cannot be blank")
    @Size(min = 8, message = "The password must contain at least 8 characters.")
    private String password;

    @NotNull(message = "This field cannot be null")
    private UserRoles role;
}
