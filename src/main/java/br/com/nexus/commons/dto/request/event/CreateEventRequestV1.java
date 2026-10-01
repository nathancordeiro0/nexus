package br.com.nexus.commons.dto.request.event;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class CreateEventRequestV1 {
    @NotBlank(message = "This field cannot be blank")
    private String name;

    @NotBlank(message = "This field cannot be blank")
    private String description;

    @NotBlank(message = "This field cannot be blank")
    private String venue;

    @NotNull(message = "This field cannot be null")
    private OffsetDateTime eventDate;
}
