package br.com.nexus.entities;

import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    private UUID id;
    private UUID orderId;
    private Ticket ticket;
    private Integer quantity;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    public BigDecimal getTotalUnitAmount() {
        return calculateTotalUnitAmount();
    }

    private BigDecimal calculateTotalUnitAmount() {
        BigDecimal ticketPrice = ticket.getPrice();

        return ticketPrice.multiply(new BigDecimal(quantity));
    }
}
