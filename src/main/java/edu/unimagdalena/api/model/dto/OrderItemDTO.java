package edu.unimagdalena.api.model.dto;

import edu.unimagdalena.api.model.entities.Order;
import edu.unimagdalena.api.model.entities.Product;

public record OrderItemDTO (
        Long id,
        Order order,
        Product product,
        Integer amount,
        Float unitPrice) {
}
