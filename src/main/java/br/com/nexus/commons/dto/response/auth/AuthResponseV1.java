package br.com.nexus.commons.dto.response.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthResponseV1 {
    private String token;
}
