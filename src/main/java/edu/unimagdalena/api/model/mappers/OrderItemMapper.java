package edu.unimagdalena.api.model.mappers;

import edu.unimagdalena.api.model.dto_save.OrderItemToSaveDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import edu.unimagdalena.api.model.entities.OrderItem;
import edu.unimagdalena.api.model.dto.OrderItemDTO;

import java.util.List;

@Mapper
public interface OrderItemMapper {

    OrderItemMapper INSTANCE = Mappers.getMapper(OrderItemMapper.class);

    //@Mapping(source = "order.id", target = "orderId")
    //@Mapping(source = "product.id", target = "productId")
    OrderItemDTO orderItemToOrderItemDto(OrderItem orderItem);

    //@Mapping(source = "orderId", target = "order.id")
    //@Mapping(source = "productId", target = "product.id")
    OrderItem orderItemDtoToOrderItem(OrderItemDTO orderItemDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product.orderItems", ignore = true)
    OrderItem orderItemToSaveDtoToOrderItem(OrderItemToSaveDto orderItemToSaveDto);

    //List<OrderItemDTO> listOrderItemsToListOrderItemDto(List<OrderItem> orderItems);
}
