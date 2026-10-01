package br.com.nexus.usecases.event;

import br.com.nexus.commons.interfaces.gateways.EventGatewayInterface;
import br.com.nexus.entities.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateEventUseCase {

    private final EventGatewayInterface eventGatewayInterface;

    public void execute(UUID eventId, UUID producerId, Event request) {
        var existingEvent = eventGatewayInterface.findById(eventId);
        existingEvent.checkOwnership(producerId);
        existingEvent.updateData(request);

        eventGatewayInterface.update(existingEvent);
    }
}
