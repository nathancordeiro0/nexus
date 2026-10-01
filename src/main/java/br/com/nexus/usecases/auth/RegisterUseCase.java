package br.com.nexus.usecases.auth;

import br.com.nexus.commons.interfaces.gateways.UserGatewayInterface;
import br.com.nexus.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterUseCase {

    private final UserGatewayInterface userGatewayInterface;
    private final PasswordEncoder passwordEncoder;

    public User execute(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userGatewayInterface.save(user);
    }
}
