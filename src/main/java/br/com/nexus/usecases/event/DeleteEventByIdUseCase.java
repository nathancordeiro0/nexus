package br.com.nexus.usecases.event;

import br.com.nexus.commons.interfaces.gateways.EventGatewayInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteEventByIdUseCase {

    private final EventGatewayInterface eventGatewayInterface;

    public void execute(UUID producerId, UUID eventId) {
        var existingEvent = eventGatewayInterface.findById(eventId);

        existingEvent.checkOwnership(producerId);

        eventGatewayInterface.delete(eventId);
    }
}
