package br.com.nexus.usecases.ticket;

import br.com.nexus.commons.interfaces.gateways.EventGatewayInterface;
import br.com.nexus.commons.interfaces.gateways.TicketGatewayInterface;
import br.com.nexus.entities.Event;
import br.com.nexus.entities.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateTicketUseCase {

    private final TicketGatewayInterface ticketGatewayInterface;
    private final EventGatewayInterface eventGatewayInterface;

    public Ticket execute(UUID eventId, UUID producerId, Ticket ticket) {
        var existingEvent = eventGatewayInterface.findById(eventId);

        existingEvent.checkOwnership(producerId);

        return ticketGatewayInterface.save(ticket.withEvent(existingEvent));
    }
}
