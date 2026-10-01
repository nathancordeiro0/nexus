package br.com.nexus.usecases.user;

import br.com.nexus.commons.interfaces.gateways.UserGatewayInterface;
import br.com.nexus.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateUserUseCase {

    private final UserGatewayInterface userGatewayInterface;

    public void execute(UUID userId, User user) {
        user.setId(userId);

        userGatewayInterface.update(user);
    }
}
