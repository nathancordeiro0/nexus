package br.com.nexus.usecases.auth;

import br.com.nexus.commons.config.JwtService;
import br.com.nexus.commons.dto.request.auth.AuthRequestV1;
import br.com.nexus.commons.dto.response.auth.AuthResponseV1;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUseCase {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthResponseV1 execute(AuthRequestV1 request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String token = jwtService.generateToken(userDetails);

        return new AuthResponseV1(token);
    }

}
