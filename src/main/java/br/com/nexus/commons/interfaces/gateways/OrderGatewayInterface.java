package br.com.nexus.commons.interfaces.gateways;

import br.com.nexus.entities.Order;

import java.util.UUID;

public interface OrderGatewayInterface {
    Order findById(UUID id);

    Order save(Order order);

    void delete(UUID id);

    void update(Order order);
}
