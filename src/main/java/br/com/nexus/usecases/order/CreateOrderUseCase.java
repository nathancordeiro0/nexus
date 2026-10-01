package br.com.nexus.usecases.order;

import br.com.nexus.commons.enums.OrderStatus;
import br.com.nexus.commons.interfaces.gateways.OrderGatewayInterface;
import br.com.nexus.commons.interfaces.gateways.OrderItemGatewayInterface;
import br.com.nexus.commons.interfaces.gateways.TicketGatewayInterface;
import br.com.nexus.entities.Order;
import br.com.nexus.entities.OrderItem;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateOrderUseCase {

    private final OrderGatewayInterface orderGatewayInterface;
    private final OrderItemGatewayInterface orderItemGatewayInterface;
    private final TicketGatewayInterface ticketGatewayInterface;

    @Transactional
    public Order execute(UUID customerId, Order order) {
        order.setCustomerId(customerId);

        var orderEntity = orderGatewayInterface.save(order.withStatus(OrderStatus.PENDING));

        List<OrderItem> orderItems = order.getItems().stream().map(item -> item.withOrderId(orderEntity.getId())).toList();

        var savedOrderItems = orderItemGatewayInterface.saveAll(orderItems);

        var orderItemsWithTicket = savedOrderItems.stream()
                .map(orderItem -> {
                    var ticket = ticketGatewayInterface.findByOrderItemId(orderItem.getId());
                    return orderItem.withTicket(ticket);
                })
                .toList();

        return orderEntity.withItems(orderItemsWithTicket);
    }
}
