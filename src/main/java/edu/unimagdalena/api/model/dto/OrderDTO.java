package edu.unimagdalena.api.model.dto;

import java.util.Collections;
import java.util.List;

import edu.unimagdalena.api.model.entities.Customer;
import edu.unimagdalena.api.model.entities.Payment;
import edu.unimagdalena.api.model.entities.ShipmentDetails;
import edu.unimagdalena.api.model.enums.OrderStatus;

public record OrderDTO (
        Long id,
        Customer customer,
        String orderDate,
        OrderStatus status,
        List<OrderItemDTO> items,
        Payment payment,
        ShipmentDetails shipmentDetails
) {
    public List<OrderItemDTO> getCopyOfItems() {
        return Collections.unmodifiableList(items);
    }
}