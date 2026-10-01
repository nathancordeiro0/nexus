package br.com.nexus.commons.interfaces.gateways;

import br.com.nexus.entities.User;

import java.util.List;
import java.util.UUID;

public interface UserGatewayInterface {
    User findById(UUID id);

    List<User> findAll();

    User findByEmail(String email);

    User save(User user);

    void delete(UUID id);

    void update(User user);
}
