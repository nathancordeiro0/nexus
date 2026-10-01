package br.com.nexus.usecases.event;

import br.com.nexus.commons.enums.EventStatus;
import br.com.nexus.commons.interfaces.gateways.EventGatewayInterface;
import br.com.nexus.commons.interfaces.gateways.UserGatewayInterface;
import br.com.nexus.entities.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateEventUseCase {

    private final EventGatewayInterface eventGatewayInterface;
    private final UserGatewayInterface userGatewayInterface;

    public Event execute(Event event, UUID producerId) {
        var producer = userGatewayInterface.findById(producerId);

        event.setProducer(producer);

        return eventGatewayInterface.save(event.withStatus(EventStatus.DRAFT));
    }
}
