package edu.unimagdalena.api.model.mappers;

import edu.unimagdalena.api.model.dto_save.OrderToSaveDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import edu.unimagdalena.api.model.entities.Order;
import edu.unimagdalena.api.model.dto.OrderDTO;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(target = "customer.orders", ignore = true)
    @Mapping(target = "items.product", ignore = true)
    Order orderDtoToOrder(OrderDTO orderDTO);

    OrderDTO orderToOrderDto(Order order);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customer.orders", ignore = true)
    Order orderToSaveDtoToOrder(OrderToSaveDto orderToSaveDto);

    OrderToSaveDto orderToOrderToSaveDto(Order order);

}