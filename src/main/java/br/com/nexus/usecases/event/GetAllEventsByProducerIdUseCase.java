package br.com.nexus.usecases.event;

import br.com.nexus.commons.interfaces.gateways.EventGatewayInterface;
import br.com.nexus.entities.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetAllEventsByProducerIdUseCase {

    private final EventGatewayInterface eventGatewayInterface;

    public List<Event> execute(UUID producerId) {
        return eventGatewayInterface.findAllByProducerId(producerId);
    }
}
