package br.com.nexus.usecases.order;

import br.com.nexus.commons.interfaces.gateways.OrderGatewayInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteOrderByIdUseCase {

    private final OrderGatewayInterface orderGatewayInterface;

    public void execute(UUID id) {
        orderGatewayInterface.delete(id);
    }
}
