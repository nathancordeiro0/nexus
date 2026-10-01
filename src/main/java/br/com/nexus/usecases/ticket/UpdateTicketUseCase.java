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
public class UpdateTicketUseCase {

    private final TicketGatewayInterface ticketGatewayInterface;
    private final EventGatewayInterface eventGatewayInterface;

    public void execute(UUID eventId, UUID producerId, UUID ticketId, Ticket request) {
        var existingEvent = eventGatewayInterface.findById(eventId);
        existingEvent.checkOwnership(producerId);

        var existingTicket = ticketGatewayInterface.findById(ticketId);
        existingTicket.checkBelongsTo(eventId);
        existingTicket.updateData(request);

        ticketGatewayInterface.update(existingTicket);
    }
}
