package br.com.nexus.adapters.controllers;

import br.com.nexus.adapters.presenters.UserMapper;
import br.com.nexus.commons.dto.request.auth.AuthRequestV1;
import br.com.nexus.commons.dto.request.user.CreateUserRequestV1;
import br.com.nexus.commons.dto.response.auth.AuthResponseV1;
import br.com.nexus.commons.dto.response.user.UserResponseV1;
import br.com.nexus.usecases.auth.LoginUseCase;
import br.com.nexus.usecases.auth.RegisterUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    private final RegisterUseCase registerUseCase;
    private final LoginUseCase loginUseCase;
    private final UserMapper mapper;

    @PostMapping("/register")
    public ResponseEntity<UserResponseV1> register(@RequestBody CreateUserRequestV1 request) {
        var newUser = registerUseCase.execute(mapper.fromRequestToEntity(request));

        var response = mapper.fromEntityToResponse(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseV1> login(@RequestBody AuthRequestV1 request) {
        var response = loginUseCase.execute(request);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
