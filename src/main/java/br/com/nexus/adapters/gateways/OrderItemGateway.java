package br.com.nexus.adapters.gateways;

import br.com.nexus.adapters.presenters.OrderItemMapper;
import br.com.nexus.commons.interfaces.datasource.OrderItemDataSource;
import br.com.nexus.commons.interfaces.gateways.OrderItemGatewayInterface;
import br.com.nexus.entities.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderItemGateway implements OrderItemGatewayInterface {

    private final OrderItemDataSource orderItemDataSource;
    private final OrderItemMapper mapper;

    @Override
    public OrderItem findById(UUID id) {
        return mapper.fromDaoToEntity(orderItemDataSource.findById(id)
                .orElse(null));
    }

    @Override
    public OrderItem save(OrderItem orderItem) {
        var request = mapper.fromEntityToDao(orderItem);

        var newOrderItem = orderItemDataSource.save(request);

        return mapper.fromDaoToEntity(newOrderItem);
    }

    @Override
    public List<OrderItem> saveAll(List<OrderItem> orderItems) {
        var newOrderItems = orderItems.stream().map(mapper::fromEntityToDao).toList();

        return orderItemDataSource.saveAll(newOrderItems)
                .stream()
                .map(mapper::fromDaoToEntity)
                .toList();
    }

}
