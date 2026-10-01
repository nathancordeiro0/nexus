package br.com.nexus.commons.dto.response.orderitem;

import br.com.nexus.commons.dto.response.ticket.TicketResponseV1;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class OrderItemResponseV1 {
    private UUID id;
    private TicketResponseV1 ticket;
    private Integer quantity;
    private BigDecimal totalUnitAmount;
}
