package edu.unimagdalena.api.service.services;

import java.util.List;

import edu.unimagdalena.api.model.dto.OrderItemDTO;
import edu.unimagdalena.api.model.dto_save.OrderItemToSaveDto;

public interface OrderItemService {

    // CRUD

    OrderItemDTO create(OrderItemToSaveDto orderItemToSaveDto);

    OrderItemDTO getOrderItemById(Long orderItemId);

    OrderItemDTO update(OrderItemToSaveDto orderItemToSaveDto, Long orderItemId);

    void delete(Long orderItemId);

    // Others methods

    List<OrderItemDTO> getAllOrderItems();

    List<OrderItemDTO> getOrderItemsByOrderId(Long orderId);

    List<OrderItemDTO> getOrderItemsByProductId(Long productId);

    Float calculateTotalSalesForProduct(String nameProduct);

}
