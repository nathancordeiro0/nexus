package br.com.nexus.commons.interfaces.gateways;

import br.com.nexus.entities.Event;

import java.util.List;
import java.util.UUID;

public interface EventGatewayInterface {
    Event findById(UUID id);

    List<Event> findAll();

    List<Event> findAllByProducerId(UUID id);

    Event save(Event event);

    void delete(UUID id);

    void update(Event event);
}
