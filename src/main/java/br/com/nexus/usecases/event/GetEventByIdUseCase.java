package br.com.nexus.usecases.event;

import br.com.nexus.commons.interfaces.gateways.EventGatewayInterface;
import br.com.nexus.entities.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetEventByIdUseCase {

    private final EventGatewayInterface eventGatewayInterface;

    public Event execute(UUID id) {
        return eventGatewayInterface.findById(id);
    }

}
