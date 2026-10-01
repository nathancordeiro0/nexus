package br.com.nexus.usecases.user;

import br.com.nexus.commons.interfaces.gateways.UserGatewayInterface;
import br.com.nexus.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetUserByIdUseCase {

    private final UserGatewayInterface userGatewayInterface;

    public User execute(UUID id) {
        return userGatewayInterface.findById(id);
    }

}
