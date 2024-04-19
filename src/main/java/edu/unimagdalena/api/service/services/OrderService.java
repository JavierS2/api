package edu.unimagdalena.api.service.services;

import java.time.LocalDateTime;
import java.util.List;

import edu.unimagdalena.api.model.dto.OrderDTO;
import edu.unimagdalena.api.model.dto_save.OrderToSaveDto;
import edu.unimagdalena.api.model.enums.OrderStatus;

public interface OrderService {
    // CRUD
    OrderDTO create(OrderToSaveDto orderToSaveDto);

    OrderDTO getOrderById(Long orderId);

    OrderDTO update(OrderToSaveDto orderToSaveDto, Long orderId);

    void delete(Long orderId);

    // Other methods

    List<OrderDTO> getAllOrders();

    List<OrderDTO> getOrdersByCustomerId(Long customerId);

    List<OrderDTO> getBetweenDates(LocalDateTime startDate, LocalDateTime endDate);

    List<OrderDTO> getByCustomerIdAndStatus(Long customerId, OrderStatus status);
}
