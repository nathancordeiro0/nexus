package br.com.nexus.usecases.ticket;

import br.com.nexus.commons.interfaces.gateways.EventGatewayInterface;
import br.com.nexus.commons.interfaces.gateways.TicketGatewayInterface;
import br.com.nexus.entities.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteTicketByIdUseCase {

    private final TicketGatewayInterface ticketGatewayInterface;
    private final EventGatewayInterface eventGatewayInterface;

    public void execute(UUID eventId, UUID producerId, UUID ticketId) {
        var existingEvent = eventGatewayInterface.findById(eventId);

        existingEvent.checkOwnership(producerId);

        ticketGatewayInterface.delete(ticketId);
    }
}
