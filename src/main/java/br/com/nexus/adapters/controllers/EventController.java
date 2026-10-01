package br.com.nexus.adapters.controllers;

import br.com.nexus.adapters.presenters.EventMapper;
import br.com.nexus.commons.config.CustomUserDetails;
import br.com.nexus.commons.dto.request.event.CreateEventRequestV1;
import br.com.nexus.commons.dto.request.event.UpdateEventRequestV1;
import br.com.nexus.commons.dto.response.event.EventResponseV1;
import br.com.nexus.usecases.event.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class EventController {

    private final GetEventByIdUseCase getEventById;
    private final GetAllEventsUseCase getAllEvents;
    private final GetAllEventsByProducerIdUseCase getAllEventsByProducerId;
    private final CreateEventUseCase createEvent;
    private final DeleteEventByIdUseCase deleteEventById;
    private final UpdateEventUseCase updateEvent;
    private final EventMapper mapper;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/events/{eventId}")
    public ResponseEntity<EventResponseV1> getById(@PathVariable UUID eventId) {
        log.debug("find request id: {}", eventId);

        var event = getEventById.execute(eventId);

        var response = mapper.fromEntityToResponse(event);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/events")
    public ResponseEntity<List<EventResponseV1>> getAll() {
        var eventsList = getAllEvents.execute();

        var response = eventsList.stream().map(mapper::fromEntityToResponse).toList();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasRole('PRODUCER')")
    @GetMapping("/events/my-events")
    public ResponseEntity<List<EventResponseV1>> getAllByProducerId(Authentication auth) {
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();

        var eventsList = getAllEventsByProducerId.execute(userDetails.getUserId());

        var response = eventsList.stream().map(mapper::fromEntityToResponse).toList();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasRole('PRODUCER')")
    @PostMapping("/events")
    public ResponseEntity<EventResponseV1> save(
            @RequestBody @Valid CreateEventRequestV1 request,
            Authentication auth
    ) {
        log.debug("create request: {}", request);

        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();

        UUID producerId = userDetails.getUserId();

        var newEvent = createEvent.execute(mapper.fromRequestToEntity(request),  producerId);

        var response = mapper.fromEntityToResponse(newEvent);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PreAuthorize("hasRole('PRODUCER')")
    @DeleteMapping("/events/{eventId}")
    public ResponseEntity<Void> delete(
            @PathVariable UUID eventId,
            Authentication auth
    ) {
        log.debug("delete request id: {}", eventId);

        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();

        UUID producerId = userDetails.getUserId();

        deleteEventById.execute(producerId, eventId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PreAuthorize("hasRole('PRODUCER')")
    @PutMapping("/events/{eventId}")
    public ResponseEntity<Void> update(
            @PathVariable UUID eventId,
            @RequestBody @Valid UpdateEventRequestV1 request,
            Authentication auth
    ) {
        log.debug("update request: {}", request);

        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();

        UUID producerId = userDetails.getUserId();

        var newEvent = mapper.fromUpdateRequestToEntity(request);

        updateEvent.execute(eventId, producerId, newEvent);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

    }
}
