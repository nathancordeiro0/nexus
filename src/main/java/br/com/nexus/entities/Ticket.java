package br.com.nexus.entities;

import br.com.nexus.commons.enums.TicketType;
import lombok.*;
import org.springframework.security.access.AccessDeniedException;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    private UUID id;
    private Event event;
    private String name;
    private BigDecimal price;
    private TicketType type;
    private Integer totalQuantity;
    private Integer soldQuantity = 0;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    public void checkBelongsTo(UUID eventId) {
        if (!this.event.getId().equals(eventId)) {
            throw new AccessDeniedException(
                    "Ticket does not belong to this event"
            );
        }
    }

    public void updateData(Ticket ticket) {
        this.name = ticket.getName();
        this.price = ticket.getPrice();
        this.type = ticket.getType();
        this.totalQuantity = ticket.getTotalQuantity();
    }
}
