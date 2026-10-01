package br.com.nexus.commons.dto.request.ticket;

import br.com.nexus.commons.enums.TicketType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class UpdateTicketRequestV1 {
    @NotBlank(message = "This field cannot be blank")
    private String name;

    @NotNull(message = "This field cannot be null")
    private BigDecimal price;

    @NotNull(message = "This field cannot be null")
    private TicketType type;

    @NotNull(message = "This field cannot be null")
    private Integer totalQuantity;
}
