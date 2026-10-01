package br.com.nexus.commons.interfaces.gateways;

import br.com.nexus.entities.Ticket;

import java.util.UUID;

public interface TicketGatewayInterface {
    Ticket findById(UUID id);

    Ticket findByOrderItemId(UUID id);

    Ticket save(Ticket ticket);

    void delete(UUID id);

    void update(Ticket ticket);
}
