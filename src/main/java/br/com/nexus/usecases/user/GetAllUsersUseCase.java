package br.com.nexus.usecases.user;

import br.com.nexus.commons.interfaces.gateways.UserGatewayInterface;
import br.com.nexus.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllUsersUseCase {

    private final UserGatewayInterface userGatewayInterface;

    public List<User> execute() {
        return userGatewayInterface.findAll();
    }

}
