package br.com.nexus.commons.dto.request.orderitem;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateOrderItemRequestV1 {
    @NotNull(message = "This field cannot be null")
    private UUID ticketId;

    @NotNull(message = "This field cannot be null")
    private Integer quantity;
}
