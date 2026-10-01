package br.com.nexus.usecases.user;

import br.com.nexus.commons.interfaces.gateways.UserGatewayInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteUserByIdUseCase {

    private final UserGatewayInterface userGatewayInterface;

    public void execute(UUID id) {
        userGatewayInterface.delete(id);
    }
}
