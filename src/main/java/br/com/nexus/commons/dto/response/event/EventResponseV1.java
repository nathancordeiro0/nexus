package br.com.nexus.commons.dto.response.event;

import br.com.nexus.commons.dto.response.user.UserResponseV1;
import br.com.nexus.commons.enums.EventStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
public class EventResponseV1 {
    private UUID id;
    private UserResponseV1 producer;
    private String name;
    private String description;
    private String venue;
    private EventStatus status;
    private OffsetDateTime eventDate;
}
