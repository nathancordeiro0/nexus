package br.com.nexus.adapters.gateways;

import br.com.nexus.adapters.presenters.OrderMapper;
import br.com.nexus.commons.interfaces.datasource.OrderDataSource;
import br.com.nexus.commons.interfaces.gateways.OrderGatewayInterface;
import br.com.nexus.entities.Order;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderGateway implements OrderGatewayInterface {

    private final OrderDataSource orderDataSource;
    private final OrderMapper mapper;
    private final EntityManager entityManager;

    @Override
    public Order findById(UUID id) {
        return mapper.fromDaoToEntity(orderDataSource.findById(id)
                .orElse(null));
    }

    @Override
    public Order save(Order order) {
        var savedOrder = mapper.fromEntityToDao(order);

        savedOrder = orderDataSource.saveAndFlush(savedOrder);

        entityManager.refresh(savedOrder);

        return mapper.fromDaoToEntity(savedOrder);
    }

    @Override
    public void delete(UUID id) {
        var OrderToDelete = findById(id);

        orderDataSource.delete(mapper.fromEntityToDao(OrderToDelete));
    }

    @Override
    public void update(Order order) {
        findById(order.getId());

        orderDataSource.save(mapper.fromEntityToDao(order));
    }

}
