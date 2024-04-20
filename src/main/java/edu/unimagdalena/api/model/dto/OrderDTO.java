package edu.unimagdalena.api.model.dto;

import java.util.Collections;
import java.util.List;
import java.time.LocalDateTime;

import edu.unimagdalena.api.model.enums.OrderStatus;

public record OrderDTO(
        Long id,
        CustomerDTO customer,
        LocalDateTime orderDate,
        OrderStatus status,
        List<OrderItemDTO> items,
        PaymentDTO payment,
        ShipmentDetailsDTO shipmentDetails) {
    public List<OrderItemDTO> getCopyOfItems() {
        return Collections.unmodifiableList(items);
    }
}