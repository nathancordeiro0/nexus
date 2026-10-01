package br.com.nexus.adapters.gateways;

import br.com.nexus.adapters.presenters.TicketMapper;
import br.com.nexus.commons.interfaces.datasource.TicketDataSource;
import br.com.nexus.commons.interfaces.gateways.TicketGatewayInterface;
import br.com.nexus.entities.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TicketGateway implements TicketGatewayInterface {

    private final TicketDataSource ticketDataSource;
    private final TicketMapper mapper;

    @Override
    public Ticket findById(UUID id) {
        return mapper.fromDaoToEntity(ticketDataSource.findById(id)
                .orElse(null));
    }

    @Override
    public Ticket findByOrderItemId(UUID id) {
        return mapper.fromDaoToEntity(ticketDataSource.findByOrderItemId(id)
                .orElse(null));
    }

    @Override
    public Ticket save(Ticket ticket) {
        var request = mapper.fromEntityToDao(ticket);

        var newTicket = ticketDataSource.save(request);

        return mapper.fromDaoToEntity(newTicket);
    }

    @Override
    public void delete(UUID id) {
        var ticketToDelete = findById(id);

        ticketDataSource.delete(mapper.fromEntityToDao(ticketToDelete));
    }

    @Override
    public void update(Ticket ticket) {
        findById(ticket.getId());

        ticketDataSource.save(mapper.fromEntityToDao(ticket));
    }

}
