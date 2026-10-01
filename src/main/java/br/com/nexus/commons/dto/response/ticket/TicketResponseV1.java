package br.com.nexus.commons.dto.response.ticket;

import br.com.nexus.commons.enums.TicketType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class TicketResponseV1 {
    private UUID id;
    private UUID eventId;
    private String name;
    private BigDecimal price;
    private TicketType type;
    private Integer totalQuantity;
    private Integer soldQuantity;
}
