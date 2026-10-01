package br.com.nexus.commons.dto.request.order;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class UpdateOrderRequestV1 {
    @NotNull(message = "This field cannot be null")
    private Long orderNumber;

    @NotNull(message = "This field cannot be null")
    private BigDecimal totalAmount;
}
