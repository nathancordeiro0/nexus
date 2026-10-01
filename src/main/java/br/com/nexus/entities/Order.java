package br.com.nexus.entities;


import br.com.nexus.commons.enums.OrderStatus;
import lombok.*;
import org.springframework.security.access.AccessDeniedException;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private UUID id;
    private UUID customerId;
    private Long number;
    private List<OrderItem> items = new ArrayList<>();
    private OrderStatus status;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    public BigDecimal getTotalAmount() {
        return calculateTotalAmount();
    }

    public BigDecimal calculateTotalAmount() {
        BigDecimal total = BigDecimal.ZERO;

        for (OrderItem item : items) {
            BigDecimal itemPrice = item.getTicket() != null && item.getTicket().getPrice() != null
                    ? item.getTicket().getPrice()
                    : BigDecimal.ZERO;

            BigDecimal quantity = item.getQuantity() != null
                    ? new BigDecimal(item.getQuantity())
                    : BigDecimal.ZERO;

            total = total.add(itemPrice.multiply(quantity));
        }

        return total;
    }

    public void checkBelongsTo(UUID customerId) {
        if (!this.customerId.equals(customerId)) {
            throw new AccessDeniedException(
                    "Order does not belong to this customer"
            );
        }
    }
}
