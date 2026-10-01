package br.com.nexus.adapters.gateways;

import br.com.nexus.adapters.presenters.EventMapper;
import br.com.nexus.commons.interfaces.datasource.EventDataSource;
import br.com.nexus.commons.interfaces.gateways.EventGatewayInterface;
import br.com.nexus.entities.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EventGateway implements EventGatewayInterface {

    private final EventDataSource eventDataSource;
    private final EventMapper mapper;

    @Override
    public Event findById(UUID id) {
        return mapper.fromDaoToEntity(eventDataSource.findById(id)
                .orElse(null));
    }

    @Override
    public List<Event> findAll() {
        return eventDataSource.findAll()
                .stream()
                .map(mapper::fromDaoToEntity)
                .toList();
    }

    @Override
    public List<Event> findAllByProducerId(UUID id) {
        return eventDataSource.findAllByProducerId(id)
                .stream()
                .map(mapper::fromDaoToEntity)
                .toList();
    }

    @Override
    public Event save(Event event) {
        var request = mapper.fromEntityToDao(event);

        var newEvent = eventDataSource.save(request);

        return mapper.fromDaoToEntity(newEvent);
    }

    @Override
    public void delete(UUID id) {
        var EventToDelete = findById(id);

        eventDataSource.delete(mapper.fromEntityToDao(EventToDelete));
    }

    @Override
    public void update(Event event) {
        findById(event.getId());

        eventDataSource.save(mapper.fromEntityToDao(event));
    }

}
