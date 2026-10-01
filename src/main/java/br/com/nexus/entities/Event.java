package br.com.nexus.entities;

import br.com.nexus.commons.enums.EventStatus;
import lombok.*;
import org.springframework.security.access.AccessDeniedException;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    private UUID id;
    private User producer;
    private String name;
    private String description;
    private String venue;
    private EventStatus status;
    private OffsetDateTime eventDate;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    public void checkOwnership(UUID producerId) {
        if (!this.producer.getId().equals(producerId)) {
            throw new AccessDeniedException(
                    "User is not the producer of this event"
            );
        }
    }

    public void updateData(Event event) {
        this.name = event.getName();
        this.description = event.getDescription();
        this.venue = event.getVenue();
        this.eventDate = event.getEventDate();
    }
}
