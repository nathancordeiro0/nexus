package br.com.nexus.adapters.presenters;

import br.com.nexus.commons.dao.EventDAO;
import br.com.nexus.commons.dto.request.event.CreateEventRequestV1;
import br.com.nexus.commons.dto.request.event.UpdateEventRequestV1;
import br.com.nexus.commons.dto.response.event.EventResponseV1;
import br.com.nexus.entities.Event;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EventMapper {

    EventDAO fromEntityToDao(Event entity);

    Event fromDaoToEntity(EventDAO dao);

    EventResponseV1 fromEntityToResponse(Event entity);

    Event fromRequestToEntity(CreateEventRequestV1 request);

    Event fromUpdateRequestToEntity(UpdateEventRequestV1 request);

}
