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

    //@Mapping(source = "customer" , target = "customer.id")
    //@Mapping(source = "payment" , target = "payment.id")
    //@Mapping(source = "shipmentDetails" , target = "shipmentDetails.id")
    Order orderDtoToOrder(OrderDTO orderDTO);

    //@Mapping(source = "customer.id", target = "customer")
    //@Mapping(source = "payment.id", target = "payment")
    //@Mapping(source = "shipmentDetails.id" , target = "shipmentDetails")
    OrderDTO
    orderToOrderDto(Order order);

    @Mapping(target = "id", ignore = true)
    Order orderToSaveDtoToOrder(OrderToSaveDto orderToSaveDto);


}