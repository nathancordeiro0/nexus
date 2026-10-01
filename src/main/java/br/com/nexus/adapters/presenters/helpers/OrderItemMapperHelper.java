package br.com.nexus.adapters.presenters.helpers;

import br.com.nexus.adapters.presenters.OrderItemMapper;
import br.com.nexus.commons.dto.request.orderitem.CreateOrderItemRequestV1;
import br.com.nexus.entities.OrderItem;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderItemMapperHelper {

    private final OrderItemMapper mapper;

    @Named("OrderItemsMapper")
    public List<OrderItem> map(List<CreateOrderItemRequestV1> orderItems) {
        return orderItems.stream().map(mapper::fromRequestToEntity).toList();
    }
}