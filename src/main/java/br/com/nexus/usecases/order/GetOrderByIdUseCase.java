package br.com.nexus.usecases.order;

import br.com.nexus.commons.interfaces.gateways.OrderGatewayInterface;
import br.com.nexus.entities.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetOrderByIdUseCase {

    private final OrderGatewayInterface orderGatewayInterface;

    public Order execute(UUID id) {
        return orderGatewayInterface.findById(id);
    }

}
