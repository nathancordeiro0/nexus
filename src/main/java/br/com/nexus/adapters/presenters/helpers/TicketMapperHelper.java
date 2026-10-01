package br.com.nexus.adapters.presenters.helpers;

import br.com.nexus.commons.interfaces.gateways.TicketGatewayInterface;
import br.com.nexus.entities.Ticket;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TicketMapperHelper {

    private final TicketGatewayInterface ticketGatewayInterface;

    @Named("TicketIdMapper")
    public Ticket map(UUID id) {
        return ticketGatewayInterface.findById(id);
    }
}