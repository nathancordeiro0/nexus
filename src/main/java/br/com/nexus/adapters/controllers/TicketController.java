package br.com.nexus.adapters.controllers;

import br.com.nexus.adapters.presenters.TicketMapper;
import br.com.nexus.commons.config.CustomUserDetails;
import br.com.nexus.commons.dto.request.ticket.CreateTicketRequestV1;
import br.com.nexus.commons.dto.request.ticket.UpdateTicketRequestV1;
import br.com.nexus.commons.dto.response.ticket.TicketResponseV1;
import br.com.nexus.usecases.ticket.CreateTicketUseCase;
import br.com.nexus.usecases.ticket.DeleteTicketByIdUseCase;
import br.com.nexus.usecases.ticket.GetTicketByIdUseCase;
import br.com.nexus.usecases.ticket.UpdateTicketUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class TicketController {

    private final GetTicketByIdUseCase getTicketById;
    private final CreateTicketUseCase createTicket;
    private final DeleteTicketByIdUseCase deleteTicketById;
    private final UpdateTicketUseCase updateTicket;
    private final TicketMapper mapper;

    @GetMapping("/events/{eventId}/tickets/{ticketId}")
    public ResponseEntity<TicketResponseV1> getById(
            @PathVariable UUID eventId,
            @PathVariable UUID ticketId
    ) {
        log.debug("find request id: {}", ticketId);

        var ticket = getTicketById.execute(eventId, ticketId);

        var response = mapper.fromEntityToResponse(ticket);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasRole('PRODUCER')")
    @PostMapping("/events/{eventId}/tickets")
    public ResponseEntity<TicketResponseV1> save(
            @PathVariable UUID eventId,
            @RequestBody @Valid CreateTicketRequestV1 request,
            Authentication auth
    ) {
        log.debug("create request: {}", request);

        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        UUID producerId = userDetails.getUserId();

        var newTicket = createTicket.execute(eventId, producerId, mapper.fromRequestToEntity(request));

        var response = mapper.fromEntityToResponse(newTicket);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PreAuthorize("hasRole('PRODUCER')")
    @DeleteMapping("/events/{eventId}/tickets/{ticketId}")
    public ResponseEntity<Void> delete(
            @PathVariable UUID eventId,
            @PathVariable UUID ticketId,
            Authentication auth
    ) {
        log.debug("delete request id: {}", ticketId);

        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        UUID producerId = userDetails.getUserId();

        deleteTicketById.execute(eventId, producerId, ticketId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PreAuthorize("hasRole('PRODUCER')")
    @PutMapping("/events/{eventId}/tickets/{ticketId}")
    public ResponseEntity<Void> update(
            @PathVariable UUID eventId,
            @PathVariable UUID ticketId,
            @RequestBody @Valid UpdateTicketRequestV1 request,
            Authentication auth
    ) {
        log.debug("update request: {}", request);

        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        UUID producerId = userDetails.getUserId();

        var newTicket = mapper.fromUpdateRequestToEntity(request);

        updateTicket.execute(eventId, producerId, ticketId, newTicket);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

    }
}
