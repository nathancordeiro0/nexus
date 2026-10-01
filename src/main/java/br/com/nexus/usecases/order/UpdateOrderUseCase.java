package br.com.nexus.usecases.order;

import br.com.nexus.commons.interfaces.gateways.OrderGatewayInterface;
import br.com.nexus.entities.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateOrderUseCase {

    private final OrderGatewayInterface orderGatewayInterface;

    public void execute(Order order) {
        orderGatewayInterface.update(order);
    }
}
