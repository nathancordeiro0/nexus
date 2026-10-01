package br.com.nexus.adapters.presenters.helpers;

import br.com.nexus.commons.interfaces.gateways.EventGatewayInterface;
import br.com.nexus.entities.Event;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EventMapperHelper {

    private final EventGatewayInterface eventGateway;

    @Named("EventIdMapper")
    public Event map(UUID id) {
        return eventGateway.findById(id);
    }
}
