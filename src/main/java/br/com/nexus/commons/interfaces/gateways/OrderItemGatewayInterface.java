package br.com.nexus.commons.interfaces.gateways;

import br.com.nexus.entities.OrderItem;

import java.util.List;
import java.util.UUID;

public interface OrderItemGatewayInterface {
    OrderItem findById(UUID id);

    OrderItem save(OrderItem orderItem);

    List<OrderItem> saveAll(List<OrderItem> orderItems);
}
