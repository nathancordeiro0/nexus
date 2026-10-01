package br.com.nexus.commons.dto.request.order;

import br.com.nexus.commons.dto.request.orderitem.CreateOrderItemRequestV1;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CreateOrderRequestV1 {
    @NotNull(message = "This field cannot be null")
    private List<CreateOrderItemRequestV1> items;

}
