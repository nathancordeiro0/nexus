package br.com.nexus.adapters.presenters;

import br.com.nexus.adapters.presenters.helpers.TicketMapperHelper;
import br.com.nexus.commons.dao.OrderItemDAO;
import br.com.nexus.commons.dto.request.orderitem.CreateOrderItemRequestV1;
import br.com.nexus.commons.dto.request.orderitem.UpdateOrderItemRequestV1;
import br.com.nexus.commons.dto.response.orderitem.OrderItemResponseV1;
import br.com.nexus.entities.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {TicketMapperHelper.class, TicketMapper.class,})
public interface OrderItemMapper {

    OrderItemDAO fromEntityToDao(OrderItem entity);

    OrderItem fromDaoToEntity(OrderItemDAO dao);

    @Mapping(target = "totalUnitAmount", expression = "java(entity.getTotalUnitAmount())")
    OrderItemResponseV1 fromEntityToResponse(OrderItem entity);

    @Mapping(source = "ticketId", target = "ticket", qualifiedByName = "TicketIdMapper")
    OrderItem fromRequestToEntity(CreateOrderItemRequestV1 request);

    OrderItem fromUpdateRequestToEntity(UpdateOrderItemRequestV1 request);

}
