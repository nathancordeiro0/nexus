package br.com.nexus.usecases.ticket;

import br.com.nexus.commons.interfaces.gateways.TicketGatewayInterface;
import br.com.nexus.entities.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetTicketByIdUseCase {

    private final TicketGatewayInterface ticketGatewayInterface;

    public Ticket execute(UUID eventId, UUID ticketId) {
        var existingTicket = ticketGatewayInterface.findById(ticketId);
        existingTicket.checkBelongsTo(eventId);

        return ticketGatewayInterface.findById(ticketId);
    }

}
