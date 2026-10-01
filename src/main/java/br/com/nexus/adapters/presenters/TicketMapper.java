package br.com.nexus.adapters.presenters;

import br.com.nexus.adapters.presenters.helpers.EventMapperHelper;
import br.com.nexus.commons.dao.TicketDAO;
import br.com.nexus.commons.dto.request.ticket.CreateTicketRequestV1;
import br.com.nexus.commons.dto.request.ticket.UpdateTicketRequestV1;
import br.com.nexus.commons.dto.response.ticket.TicketResponseV1;
import br.com.nexus.entities.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = EventMapperHelper.class)
public interface TicketMapper {

    TicketDAO fromEntityToDao(Ticket entity);

    Ticket fromDaoToEntity(TicketDAO dao);

    @Mapping(source = "event.id", target = "eventId")
    TicketResponseV1 fromEntityToResponse(Ticket entity);

    Ticket fromRequestToEntity(CreateTicketRequestV1 request);

    Ticket fromUpdateRequestToEntity(UpdateTicketRequestV1 request);

}
