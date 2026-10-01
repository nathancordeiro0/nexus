package br.com.nexus.commons.dto.response.order;

import br.com.nexus.commons.dto.response.orderitem.OrderItemResponseV1;
import br.com.nexus.commons.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class OrderResponseV1 {
    private UUID id;
    private UUID customerId;
    private Long number;
    private List<OrderItemResponseV1> items;
    private OrderStatus status;
    private BigDecimal totalAmount;
}
