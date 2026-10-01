package br.com.nexus.commons.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequestV1 {
    @NotBlank(message = "This field cannot be blank")
    private String email;

    @NotBlank(message = "This field cannot be blank")
    private String password;
}
