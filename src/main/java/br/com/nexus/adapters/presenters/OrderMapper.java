package br.com.nexus.adapters.presenters;

import br.com.nexus.adapters.presenters.helpers.OrderItemMapperHelper;
import br.com.nexus.commons.dao.OrderDAO;
import br.com.nexus.commons.dto.request.order.CreateOrderRequestV1;
import br.com.nexus.commons.dto.response.order.OrderResponseV1;
import br.com.nexus.entities.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {OrderItemMapperHelper.class, OrderItemMapper.class})
public interface OrderMapper {

    @Mapping(target = "number", ignore = true)
    OrderDAO fromEntityToDao(Order entity);

    @Mapping(source = "number", target = "number")
    Order fromDaoToEntity(OrderDAO dao);

    @Mapping(target = "totalAmount", expression = "java(entity.getTotalAmount())")
    OrderResponseV1 fromEntityToResponse(Order entity);

    @Mapping(source = "request.items", target = "items", qualifiedByName = "OrderItemsMapper")
    Order fromRequestToEntity(CreateOrderRequestV1 request);
}
